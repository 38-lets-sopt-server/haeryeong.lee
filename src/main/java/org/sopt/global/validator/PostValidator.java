package org.sopt.global.validator;

import org.sopt.global.code.status.ErrorCode;
import org.sopt.global.exception.GeneralException;

public class PostValidator {
  public static void validatePostId(Long postId) {
    if (postId == null || postId <= 0) {
      throw new GeneralException(ErrorCode.INVALID_POST_ID);
    }
  }

  public static void validatePostTitle(String title) {
    if (title == null || title.trim().isEmpty()) {
      throw new GeneralException(ErrorCode.TITLE_REQUIRED);
    }

    if (title.length() > 50) {
      throw new GeneralException(ErrorCode.TITLE_TOO_LONG);
    }
  }
}
