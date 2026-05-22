package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")  // "user"는 SQL 예약어라 테이블명을 변경해요
public class User { //extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nickname;

  private String email;

  private String password;

  private String provider;

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
