package org.sopt.dto.request;

import org.sopt.domain.BoardType;

// 게시글 작성 요청 (클라이언트 → 서버)
public class CreatePostRequest {
  private String title;
  private String content;
  private String author;
  private boolean isQuestion;
  private boolean isAnonymous;
  private BoardType boardType;

  public CreatePostRequest(String title, String content, String author, boolean isQuestion, boolean isAnonymous, BoardType boardType) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.isQuestion = isQuestion;
    this.isAnonymous = isAnonymous;
    this.boardType = boardType;
  }

  public String getTitle() { return title; }
  public String getContent() { return content; }
  public String getAuthor() { return author; }
  public boolean isQuestion() { return isQuestion; }
  public boolean isAnonymous() { return isAnonymous; }
  public BoardType getBoardType() { return boardType; }
}

