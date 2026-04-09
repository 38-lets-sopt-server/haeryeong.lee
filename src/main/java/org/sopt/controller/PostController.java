package org.sopt.controller;

import java.util.List;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.ReadPostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;

public class PostController {
  private final PostService postService = new PostService();

  // POST /posts
  public CreatePostResponse createPost(CreatePostRequest request) {
    try {
      return postService.createPost(request);
    } catch (IllegalArgumentException e) {
      return new CreatePostResponse(null, "🚫 " + e.getMessage());
    }
  }

  // GET /posts 📝 과제
  public List<PostResponse> getAllPosts() {
    try {
      return postService.getAllPosts();
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  // GET /posts/{id} 📝 과제
  public PostResponse getPost(ReadPostRequest request) {
    try {
      return postService.getPost(request.id);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  // PUT /posts/{id} 📝 과제
  public void updatePost(Long id, String newTitle, String newContent) {
    try {
      postService.updatePost(id, newTitle, newContent);
      System.out.println("게시글 수정 완료");
    } catch (IllegalArgumentException e) {
      System.out.println("🚫 " + e.getMessage());
    }
  }

  // DELETE /posts/{id} 📝 과제
  public void deletePost(Long id) {
    // TODO: postService.deletePost() 호출, 예외 발생 시 에러 메시지 출력
  }
}