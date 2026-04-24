package org.sopt.controller;

import org.sopt.domain.BoardType;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.code.status.SuccessStatus;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // POST /posts ✅ 같이 구현
  @PostMapping
  public ResponseEntity<ApiResponse<CreatePostResponse>> createPost(
      @RequestBody CreatePostRequest request
  ) {
    CreatePostResponse response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of(SuccessStatus.POST_CREATED, response));
  }

  // GET /posts 📝 과제
  @GetMapping
  public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) BoardType boardType
  ) {
    PostListResponse response = postService.getAllPosts(page, size, boardType);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POSTS_FOUND, response));
  }

  // GET /posts/{id} 📝 과제
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<PostResponse>> getPost(
      @PathVariable Long id
  ) {
    PostResponse response = postService.getPost(id);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(response));
  }

  // PUT /posts/{id} 📝 과제
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Long>> updatePost(
      @PathVariable Long id,
      @RequestBody UpdatePostRequest request
  ) {
    Long updatedId = postService.updatePost(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.of(SuccessStatus.POST_UPDATED, updatedId));
  }

  // DELETE /posts/{id} 📝 과제
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Long>> deletePost(
      @PathVariable Long id
  ) {
    Long deletedPostId = postService.deletePost(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.of(SuccessStatus.POST_DELETED, deletedPostId));
  }
}
