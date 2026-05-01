package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 수정 요청")
public class UpdatePostRequest {

  @Schema(description = "수정할 제목", example = "수정된 제목")
  public String title;

  @Schema(description = "수정할 내용", example = "수정된 내용입니다.")
  public String content;

  @Schema(description = "질문 게시글 여부", example = "false")
  public boolean isQuestion;

  @Schema(description = "익명 여부", example = "true")
  public boolean isAnonymous;

  public UpdatePostRequest(String title, String content, boolean isQuestion, boolean isAnonymous) {
    this.title = title;
    this.content = content;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
  }

  public String getTitle() { return title; }
  public String getContent() { return content; }
  public boolean isQuestion() { return isQuestion; }
  public boolean isAnonymous() { return isAnonymous; }
}
