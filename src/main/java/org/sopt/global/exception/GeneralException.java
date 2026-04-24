package org.sopt.global.exception;

import org.sopt.dto.response.ApiResponse;
import org.sopt.global.code.status.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralException {

  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handlePostNotFound(PostNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.onFailure(e.getErrorStatus(), null)
        );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse<>(false, ErrorStatus._BAD_REQUEST.getCode(), e.getMessage(), null)
        );
  }
}