package org.sopt.dto.response;

public class UpdatePostResponse {
  public Long id;
  public String title;
  public String content;
  public String message;

  public UpdatePostResponse(Long id, String title, String content, String message) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.message = message;
  }
}
