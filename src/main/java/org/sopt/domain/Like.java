package org.sopt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
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

  protected Like() {}

  public Like(User user, Post post) {
    this.user = user;
    this.post = post;
    this.isLiked = true;
  }

  public void toggle() {
    this.isLiked = !this.isLiked;
  }

  public boolean isLiked() {
    return isLiked;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Post getPost() {
    return post;
  }
}
