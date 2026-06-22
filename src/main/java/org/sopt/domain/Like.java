package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "likes",
    indexes = {
        @Index(name = "idx_likes_user_id_post_id", columnList = "user_id, post_id")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_likes_user_id_post_id", columnNames = {"user_id", "post_id"})
    }
)
public class Like extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  private boolean isLiked;

  @Builder
  private Like(User user, Post post) {
    this.user = user;
    this.post = post;
    this.isLiked = true;
  }

  public void toggle() {
    this.isLiked = !this.isLiked;
  }
}
