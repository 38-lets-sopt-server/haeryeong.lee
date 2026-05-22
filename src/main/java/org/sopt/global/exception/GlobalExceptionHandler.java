package org.sopt.global.exception;

import org.sopt.dto.response.ApiResponse;
import org.sopt.global.code.status.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handlePostNotFoundException(PostNotFoundException e) {
    ErrorCode errorCode = e.getErrorCode();
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(ApiResponse.onFailure(errorCode, null));
  }

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneralException(GeneralException e) {
    ErrorCode errorCode = e.getErrorCode();
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(ApiResponse.onFailure(errorCode, null));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e
  ) {
    return ResponseEntity.status(ErrorCode.BAD_REQUEST.getHttpStatus()).body(ApiResponse.onFailure(ErrorCode.BAD_REQUEST, null));
  }
}
