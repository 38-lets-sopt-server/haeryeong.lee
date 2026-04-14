package org.sopt.global.code.status;

import org.sopt.global.code.BaseCode;

public enum ErrorStatus implements BaseCode {
  _BAD_REQUEST("COMMON400", "잘못된 요청입니다."),
  POST_NOT_FOUND("POST4001", "게시글을 찾을 수 없습니다."),
  INVALID_POST_ID("POST4002", "유효한 ID를 입력해주세요."),
  TITLE_REQUIRED("POST4003", "제목은 필수입니다."),
  CONTENT_REQUIRED("POST4004", "내용은 필수입니다.");

  private final String code;
  private final String message;

  ErrorStatus(String code, String message) {
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