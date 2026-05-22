package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlacklistedAccessToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 1000)
  private String token;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private BlacklistedAccessToken(String token, Long userId, LocalDateTime expiresAt) {
    this.token = token;
    this.userId = userId;
    this.expiresAt = expiresAt;
  }

  public static BlacklistedAccessToken of(String token, Long userId, LocalDateTime expiresAt) {
    return new BlacklistedAccessToken(token, userId, expiresAt);
  }
}
