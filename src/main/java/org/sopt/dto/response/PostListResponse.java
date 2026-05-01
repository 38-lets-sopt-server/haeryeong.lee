package org.sopt.dto.response;

import java.util.List;

public record PostListResponse(
  List<PostResponse> posts,
  int listSize,
  int totalPages,
  int totalElements,
  boolean isFirst,
  boolean isLast
) {
}