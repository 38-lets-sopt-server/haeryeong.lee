package org.sopt.dto.response;

import org.sopt.domain.Post;

public class PostResponse {
  public Long id;
  public String title;
  public String content;
  public String author;
  public String createdAt;
  public boolean isAnonymous;
  public boolean isQuestion;

  public PostResponse(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
    this.createdAt = post.getCreatedAt();
    this.isAnonymous = post.isAnonymous();
    this.isQuestion = post.isQuestion();
  }
}
