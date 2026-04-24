package org.sopt.dto.response;

import java.util.List;

public class PostListResponse {

  private List<PostResponse> posts;
  private int listSize;
  private int totalPages;
  private int totalElements;
  private boolean isFirst;
  private boolean isLast;

  public PostListResponse(List<PostResponse> posts, int listSize, int totalPages, int totalElements, boolean isFirst, boolean isLast) {
    this.posts = posts;
    this.listSize = listSize;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.isFirst = isFirst;
    this.isLast = isLast;
  }

  public List<PostResponse> getPosts() { return posts; }
  public int getListSize() { return listSize; }
  public int getTotalPages() { return totalPages; }
  public int getTotalElements() { return totalElements; }
  public boolean isFirst() { return isFirst; }
  public boolean isLast() { return isLast; }
}