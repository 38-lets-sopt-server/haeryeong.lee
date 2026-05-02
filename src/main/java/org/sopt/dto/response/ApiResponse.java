package org.sopt.dto.response;

import org.sopt.global.code.BaseCode;
import org.sopt.global.code.status.SuccessStatus;

public record ApiResponse<T>(
    boolean success,
    String code,
    String message,
    T result
) {

  public static <T> ApiResponse<T> onSuccess(T result) {
    return new ApiResponse<>(
        true,
        SuccessStatus._OK.getCode(),
        SuccessStatus._OK.getMessage(),
        result
    );
  }

  public static <T> ApiResponse<T> of(BaseCode code, T result) {
    return new ApiResponse<>(
        true,
        code.getCode(),
        code.getMessage(),
        result
    );
  }

  public static <T> ApiResponse<T> onFailure(BaseCode code, T result) {
    return new ApiResponse<>(
        false,
        code.getCode(),
        code.getMessage(),
        result
    );
  }
}
