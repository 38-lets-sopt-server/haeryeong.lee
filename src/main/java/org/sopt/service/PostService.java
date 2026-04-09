package org.sopt.service;

import java.util.Arrays;
import java.util.List;
import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.exception.PostNotFoundException;
import org.sopt.global.validator.PostValidator;
import org.sopt.repository.PostRepository;

public class PostService {
  private final PostRepository postRepository = new PostRepository();

  // CREATE
  public CreatePostResponse createPost(CreatePostRequest request) {
    PostValidator.validatePostTitle(request.title);
    PostValidator.validatePostContent(request.content);

    String createdAt = java.time.LocalDateTime.now().toString();
    Post post = new Post(postRepository.generateId(), request.title, request.content, request.author, createdAt);
    postRepository.save(post);
    return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
  }

  // READ - 전체 📝 과제
  public List<PostResponse> getAllPosts() {
    Post[] posts = postRepository.findAll();
    return Arrays.stream(posts).map(PostResponse::new).toList();
  }

  // READ - 단건 📝 과제
  public PostResponse getPost(Long id) {
    PostValidator.validatePostId(id);

    Post post = postRepository.findById(id);

    if (post == null) {
      throw new PostNotFoundException();
    }

    return new PostResponse(post);
  }

  // UPDATE 📝 과제
  public void updatePost(Long id, String newTitle, String newContent) {
    PostValidator.validatePostId(id);
    PostValidator.validatePostTitle(newTitle);

    Post post = postRepository.findById(id);

    if (post == null) {
      throw new PostNotFoundException();
    }

    post.update(newTitle, newContent);
    postRepository.save(post);
  }

  // DELETE 📝 과제
  public void deletePost(Long id) {
    PostValidator.validatePostId(id);

    Post post = postRepository.findById(id);

    if (post == null) {
      throw new PostNotFoundException();
    }

    postRepository.delete(post);
  }
}