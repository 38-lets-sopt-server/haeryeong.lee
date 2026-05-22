package org.sopt.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.BlacklistedAccessToken;
import org.sopt.domain.RefreshToken;
import org.sopt.domain.User;
import org.sopt.dto.request.SignUpRequest;
import org.sopt.dto.response.TokenResponse;
import org.sopt.dto.response.UserResponse;
import org.sopt.global.code.status.ErrorCode;
import org.sopt.global.exception.GeneralException;
import org.sopt.repository.BlacklistedAccessTokenRepository;
import org.sopt.repository.RefreshTokenRepository;
import org.sopt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final BlacklistedAccessTokenRepository blacklistedAccessTokenRepository;

  @Value("${security.jwt.refresh-token-expires-in-seconds:1209600}")
  private long refreshTokenExpiresInSeconds;

  public UserResponse loginWithCredentials(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new GeneralException(ErrorCode.INVALID_PASSWORD);
    }

    return UserResponse.from(user);
  }

  @Transactional
  public TokenResponse login(String email, String password) {
    UserResponse user = loginWithCredentials(email, password);

    String accessToken = jwtService.generateAccessToken(user.id(), user.email());
    String refreshToken = jwtService.generateRefreshToken(user.id());

    // 기존 Refresh Token 삭제 후 새로 저장
    refreshTokenRepository.deleteByUserId(user.id());
    refreshTokenRepository.save(
        RefreshToken.of(user.id(), refreshToken, refreshTokenExpiresInSeconds)
    );

    return TokenResponse.of(accessToken, refreshToken);
  }

  @Transactional
  public UserResponse signUp(SignUpRequest request) {
    userRepository.findByEmail(request.email()).ifPresent(user -> {
      throw new GeneralException(ErrorCode.EMAIL_ALREADY_EXISTS);
    });

    User user = new User(request.nickname(), request.email(), passwordEncoder.encode(request.password()));

    return UserResponse.from(userRepository.save(user));
  }

  public UserResponse getUserById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
    return UserResponse.from(user);
  }

  @Transactional
  public TokenResponse reissue(String refreshToken) {
    RefreshToken savedToken = refreshTokenRepository.findByToken(refreshToken)
        .orElseThrow(() -> new GeneralException(ErrorCode.INVALID_REFRESH_TOKEN));

    Long userId = jwtService.verifyAndGetUserId(refreshToken);

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

    String newAccessToken = jwtService.generateAccessToken(user.getId(), user.getEmail());
    String newRefreshToken = jwtService.generateRefreshToken(user.getId());

    savedToken.rotate(newRefreshToken, refreshTokenExpiresInSeconds);

    return TokenResponse.of(newAccessToken, newRefreshToken);
  }

  @Transactional
  public void logout(Long userId, String accessToken) {
    refreshTokenRepository.deleteByUserId(userId);

    LocalDateTime expiresAt = jwtService.getExpiration(accessToken);

    blacklistedAccessTokenRepository.save(BlacklistedAccessToken.of(accessToken, userId, expiresAt));
  }
}