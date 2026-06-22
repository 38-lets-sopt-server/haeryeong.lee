package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_users_email", columnList = "email")
    }
)
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String nickname;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(length = 255)
  private String password;

  @Column(length = 20)
  private String provider;

  @Column(length = 100)
  private String providerId;

  @Builder
  private User(String nickname, String email, String password, String provider, String providerId) {
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.provider = provider;
    this.providerId = providerId;
  }
}
