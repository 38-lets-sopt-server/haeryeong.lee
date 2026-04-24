package org.sopt.global.validator;

public class PostValidator {
  public static void validatePostId(Long postId) {
    if (postId == null || postId <= 0) {
      throw new IllegalArgumentException("유효한 게시글 ID를 입력해주세요.");
    }
  }

  public static void validatePostTitle(String title) {
    if (title == null || title.trim().isEmpty()) {
      throw new IllegalArgumentException("게시글 제목은 필수입니다.");
    }

    if (title.length() > 50) {
      throw new IllegalArgumentException("게시글 제목은 50자 이하로 입력해주세요.");
    }
  }

  public static void validatePostContent(String content) {
    if (content == null || content.trim().isEmpty()) {
      throw new IllegalArgumentException("게시글 내용은 필수입니다.");
    }
  }
}
