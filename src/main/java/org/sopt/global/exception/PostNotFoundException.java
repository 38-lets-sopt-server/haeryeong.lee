package org.sopt.global.exception;

import org.sopt.global.code.status.ErrorStatus;

public class PostNotFoundException extends RuntimeException {

  private final ErrorStatus errorStatus;

  public PostNotFoundException(ErrorStatus errorStatus) {
    super(errorStatus.getMessage());
    this.errorStatus = errorStatus;
  }

  public ErrorStatus getErrorStatus() {
    return errorStatus;
  }
}