package org.sopt.global.code.status;

import org.sopt.global.code.BaseCode;

public enum SuccessStatus implements BaseCode {
  _OK("COMMON200", "성공"),
  POST_CREATED("POST201", "게시글 작성 성공"),
  POST_FOUND("POST2001", "게시글 조회 성공"),
  POSTS_FOUND("POST2002", "전체 게시글 조회 성공"),
  POST_UPDATED("POST2003", "게시글 수정 성공"),
  POST_DELETED("POST2004", "게시글 삭제 성공"),
  LIKE_CREATED("LIKE201", "좋아요 추가 성공"),
  LIKE_DELETED("LIKE2001", "좋아요 취소 성공");

  private final String code;
  private final String message;

  SuccessStatus(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}