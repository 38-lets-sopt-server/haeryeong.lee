package org.sopt.global.exception;

import org.sopt.global.code.status.ErrorCode;

public class PostNotFoundException extends RuntimeException {
  private final ErrorCode errorCode;

  public PostNotFoundException() {
    super(ErrorCode.POST_NOT_FOUND.getMessage());
    this.errorCode = ErrorCode.POST_NOT_FOUND;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
