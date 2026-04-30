package org.sopt.dto.request;

public class UpdatePostRequest {
  public String title;
  public String content;
  public boolean isQuestion;
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
