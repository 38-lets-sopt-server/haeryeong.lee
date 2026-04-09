package org.sopt.dto.response;

public class DeletePostResponse {
  public Long id;
  public String message;

  public DeletePostResponse(Long id, String message) {
    this.id = id;
    this.message = message;
  }
}
