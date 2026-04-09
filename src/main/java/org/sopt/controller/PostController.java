package org.sopt.controller;

import java.util.List;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.ApiResponse;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.code.status.ErrorStatus;
import org.sopt.global.code.status.SuccessStatus;
import org.sopt.global.exception.PostNotFoundException;
import org.sopt.service.PostService;

public class PostController {
  private final PostService postService = new PostService();

  // POST /posts
  public ApiResponse<CreatePostResponse> createPost(CreatePostRequest request) {
    try {
      CreatePostResponse response = postService.createPost(request);
      return ApiResponse.of(SuccessStatus.POST_CREATED, response);
    } catch (IllegalArgumentException e) {
      return ApiResponse.onFailure(ErrorStatus._BAD_REQUEST, null);
    }
  }

  // GET /posts 📝 과제
  public ApiResponse<List<PostResponse>> getAllPosts() {
    List<PostResponse> posts = postService.getAllPosts();
    return ApiResponse.of(SuccessStatus.POSTS_FOUND, posts);
  }

  // GET /posts/{id} 📝 과제
  public ApiResponse<PostResponse> getPost(Long id) {
    try {
      PostResponse post = postService.getPost(id);
      return ApiResponse.of(SuccessStatus.POST_FOUND, post);
    } catch (PostNotFoundException e) {
      return ApiResponse.onFailure(ErrorStatus.POST_NOT_FOUND, null);
    } catch (IllegalArgumentException e) {
      return ApiResponse.onFailure(ErrorStatus.INVALID_POST_ID, null);
    }
  }

  // PUT /posts/{id} 📝 과제
  public ApiResponse<Void> updatePost(Long id, String newTitle, String newContent) {
    try {
      postService.updatePost(id, newTitle, newContent);
      return ApiResponse.of(SuccessStatus.POST_UPDATED, null);
    } catch (PostNotFoundException e) {
      return ApiResponse.onFailure(ErrorStatus.POST_NOT_FOUND, null);
    } catch (IllegalArgumentException e) {
      return ApiResponse.onFailure(ErrorStatus._BAD_REQUEST, null);
    }
  }

  // DELETE /posts/{id} 📝 과제
  public ApiResponse<Void> deletePost(Long id) {
    try {
      postService.deletePost(id);
      return ApiResponse.of(SuccessStatus.POST_DELETED, null);
    } catch (PostNotFoundException e) {
      return ApiResponse.onFailure(ErrorStatus.POST_NOT_FOUND, null);
    } catch (IllegalArgumentException e) {
      return ApiResponse.onFailure(ErrorStatus.INVALID_POST_ID, null);
    }
  }
}