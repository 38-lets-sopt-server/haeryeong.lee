package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity  // "이 클래스를 DB 테이블과 매핑해요" — 영속성 컨텍스트가 이 클래스를 관리해요
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    indexes = {
        @Index(name = "idx_post_board_type", columnList = "board_type")
    }
)
public class Post extends BaseTimeEntity {

  @Id // 앞에서 배운 PK
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)  // User : Post = 1 : N
  @JoinColumn(name = "user_id")       // post 테이블에 user_id FK 컬럼이 생겨요
  private User user;

  @Column(nullable = false, name = "is_question")
  private boolean isQuestion;

  @Column(nullable = false, name = "is_anonymous")
  private boolean isAnonymous;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "board_type", length = 20)
  private BoardType boardType;

  @Builder
  private Post(String title, String content, User user, boolean isQuestion, boolean isAnonymous, BoardType boardType) {
    this.title = title;
    this.content = content;
    this.user = user;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
    this.boardType = boardType;
  }

  public void update(String title, String content, boolean isQuestion, boolean isAnonymous) {
    this.title = title;
    this.content = content;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
  }

  public String getAuthor() { return this.user.getNickname(); }
}
