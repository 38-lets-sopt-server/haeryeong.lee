package org.sopt.global.code.status;

import org.sopt.global.code.BaseCode;
import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseCode {
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
  INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "COMMON4001", "page는 0 이상이어야 합니다."),
  INVALID_PAGE_SIZE(HttpStatus.BAD_REQUEST, "COMMON4002", "size는 1 이상이어야 합니다."),
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST4001", "게시글을 찾을 수 없습니다."),
  INVALID_POST_ID(HttpStatus.BAD_REQUEST, "POST4002", "유효한 ID를 입력해주세요."),
  TITLE_REQUIRED(HttpStatus.BAD_REQUEST, "POST4003", "제목은 필수입니다."),
  TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, "POST4004", "게시글 제목은 50자 이하로 입력해주세요."),
  CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, "POST4005", "내용은 필수입니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "사용자를 찾을 수 없습니다."),
  EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER4002", "이미 존재하는 이메일입니다."),
  INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "AUTH4001", "유효하지 않은 Refresh Token입니다."),
  FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH4002", "권한이 없습니다."),
  INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "AUTH4003", "잘못된 비밀번호입니다."),
  SOCIAL_LOGIN_USER(HttpStatus.BAD_REQUEST, "AUTH4004", "소셜 로그인으로 가입된 계정입니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  ErrorCode(HttpStatus httpStatus, String code, String message) {
    this.httpStatus = httpStatus;
    this.code = code;
    this.message = message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
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
