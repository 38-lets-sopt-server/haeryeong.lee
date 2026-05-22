package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.domain.BoardType;

// 게시글 작성 요청 (클라이언트 → 서버)
@Schema(description = "게시글 작성 요청")
public record CreatePostRequest(

  @Schema(description = "게시글 제목", example = "오늘 학식 뭐임")
  @NotBlank(message = "제목은 필수입니다.")
  @Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
  String title,

  @Schema(description = "게시글 내용", example = "돈까스래")
  String content,

  @Schema(description = "작성자명", example = "익명")
  String author,

  @Schema(description = "질문 게시글 여부", example = "false")
  boolean isQuestion,

  @Schema(description = "익명 여부", example = "true")
  boolean isAnonymous,

  @Schema(description = "게시판 타입", example = "FREE")
  @NotNull(message = "게시판 타입은 필수입니다.")
  BoardType boardType
) {
}

