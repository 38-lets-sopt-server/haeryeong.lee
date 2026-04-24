package org.sopt.global.exception;

import org.sopt.global.code.status.ErrorCode;

public class GeneralException extends RuntimeException {
  private final ErrorCode errorCode;

  public GeneralException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
