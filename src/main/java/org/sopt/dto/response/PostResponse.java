package org.sopt.dto.response;

import org.sopt.domain.BoardType;
import org.sopt.domain.Post;

public record PostResponse(
  Long id,
  String title,
  String content,
  String author,
  String createdAt,
  boolean isAnonymous,
  boolean isQuestion,
  BoardType boardType
) {

  public static PostResponse from(Post post) {
    return new PostResponse(
      post.getId(),
      post.getTitle(),
      post.getContent(),
      post.isAnonymous() ? "익명" : post.getAuthor(),
      post.getCreatedAt().toString(),
      post.isAnonymous(),
      post.isQuestion(),
      post.getBoardType()
    );
  }
}
