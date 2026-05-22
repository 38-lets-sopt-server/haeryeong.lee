package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  @Builder
  private RefreshToken(Long userId, String token, LocalDateTime expiresAt) {
    this.userId = userId;
    this.token = token;
    this.expiresAt = expiresAt;
  }

  public static RefreshToken of(Long userId, String token, long expiresInSeconds) {
    return RefreshToken.builder()
        .userId(userId)
        .token(token)
        .expiresAt(LocalDateTime.now().plusSeconds(expiresInSeconds))
        .build();
  }

  public void rotate(String newToken, long expiresInSeconds) {
    this.token = newToken;
    this.expiresAt = LocalDateTime.now().plusSeconds(expiresInSeconds);
  }
}
