package org.sopt.dto.response;

import org.sopt.domain.Post;

public class PostResponse {
  public Long id;
  public String title;
  public String content;
  public String author;
  public String createdAt;

  public PostResponse(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.author = post.getAuthor();
    this.createdAt = post.getCreatedAt();
  }

  @Override
  public String toString() {
    return "PostResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author='" + author + '\'' +
            ", createdAt='" + createdAt + '\'' +
            '}';
  }
}
