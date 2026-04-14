package org.sopt.dto.response;

import org.sopt.global.code.BaseCode;
import org.sopt.global.code.status.SuccessStatus;

public class ApiResponse<T> {
  private final boolean success;
  private final String code;
  private final String message;
  private final T result;

  public ApiResponse(boolean success, String code, String message, T result) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.result = result;
  }

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

  public boolean isSuccess() {
    return success;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getResult() {
    return result;
  }

  @Override
  public String toString() {
    return "success=" + success +
        "\ncode=" + code +
        "\nmessage=" + message +
        "\nresult=" + result;
  }
}