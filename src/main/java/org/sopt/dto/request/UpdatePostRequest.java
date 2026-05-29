package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest(

  @Schema(description = "수정할 제목", example = "수정된 제목")
  @NotBlank(message = "제목은 필수입니다.")
  @Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
  String title,

  @Schema(description = "수정할 내용", example = "수정된 내용입니다.")
  String content,

  @Schema(description = "질문 게시글 여부", example = "false")
  boolean isQuestion,

  @Schema(description = "익명 여부", example = "true")
  boolean isAnonymous
) {
}
