package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.request.SignUpRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.TokenResponse;
import org.sopt.dto.response.UserResponse;
import org.sopt.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "로그인 (Access Token + Refresh Token 발급)")
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<TokenResponse>> login(
      @RequestParam("email") String email,
      @RequestParam("password") String password
  ) {
    TokenResponse tokens = authService.login(email, password);

    return ResponseEntity.ok(ApiResponse.onSuccess(tokens));
  }

  @Operation(summary = "내 정보 조회 (Access Token 검증)")
  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponse>> me(Authentication authentication) {

    if (authentication == null || authentication.getPrincipal() == null) {
      throw new IllegalArgumentException("인증되지 않았습니다.");
    }

    Long userId = Long.parseLong(authentication.getName());
    UserResponse user = authService.getUserById(userId);

    return ResponseEntity.ok(ApiResponse.onSuccess(user));
  }

  @Operation(summary = "회원가입")
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<UserResponse>> signUp(
      @RequestBody SignUpRequest request
  ) {
    UserResponse user = authService.signUp(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(user));
  }

  @Operation(summary = "Access Token 재발급")
  @PostMapping("/reissue")
  public ResponseEntity<ApiResponse<TokenResponse>> reissue(
      @RequestParam("refreshToken") String refreshToken
  ) {
    TokenResponse tokens = authService.reissue(refreshToken);
    return ResponseEntity.ok(ApiResponse.onSuccess(tokens));
  }

  @Operation(summary = "로그아웃 (Access Token 블랙리스트 등록)")
  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logout(
      Authentication authentication,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
  ) {
    Long userId = Long.parseLong(authentication.getName());
    String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
    authService.logout(userId, accessToken);
    return ResponseEntity.ok(ApiResponse.onSuccess(null));
  }
}