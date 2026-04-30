package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity  // "이 클래스를 DB 테이블과 매핑해요" — 영속성 컨텍스트가 이 클래스를 관리해요
public class Post {

  @Id // 앞에서 배운 PK
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
  @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요
  private User user;

  private boolean isQuestion;
  private boolean isAnonymous;
  private BoardType boardType;
  private LocalDateTime createdAt;

  protected Post() {}  // JPA 기본 생성자

  public Post(String title, String content, User user, boolean isQuestion, boolean isAnonymous, BoardType boardType) {
    this.title = title;
    this.content = content;
    this.user = user;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
    this.createdAt = LocalDateTime.now();
    this.boardType = boardType;
  }

  public void update(String title, String content, boolean isQuestion, boolean isAnonymous) {
    this.title = title;
    this.content = content;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public BoardType getBoardType() {
    return this.boardType;
  }
}