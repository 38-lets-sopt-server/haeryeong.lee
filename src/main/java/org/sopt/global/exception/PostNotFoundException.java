package org.sopt.global.exception;

public class PostNotFoundException extends RuntimeException {

  private static final String MESSAGE = "게시글을 찾을 수 없습니다.";

  public PostNotFoundException() {
    super(MESSAGE);
  }
}