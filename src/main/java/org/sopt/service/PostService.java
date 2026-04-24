package org.sopt.service;

import java.util.Arrays;
import java.util.List;
import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.code.status.ErrorStatus;
import org.sopt.global.exception.PostNotFoundException;
import org.sopt.global.validator.PostValidator;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public CreatePostResponse createPost(CreatePostRequest request) {
    PostValidator.validatePostTitle(request.getTitle());
    Long id = postRepository.generateId();

    Post post = new Post(id, request.getTitle(), request.getContent(), request.getAuthor(), request.isQuestion(), request.isAnonymous());
    Post savedPost = postRepository.save(post);
    return new CreatePostResponse(savedPost.getId(), "게시글 등록 완료!");
  }

  public List<PostResponse> getAllPosts() {
    return postRepository.findAll().stream()
        .map(PostResponse::new)
        .toList();
  }

  public PostResponse getPost(Long id) {
    PostValidator.validatePostId(id);
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(ErrorStatus.POST_NOT_FOUND));
    return new PostResponse(post);
  }

  public Long updatePost(Long id, UpdatePostRequest request) {
    PostValidator.validatePostTitle(request.getTitle());

    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(ErrorStatus.POST_NOT_FOUND));

    post.update(request.title, request.content, request.isQuestion(), request.isAnonymous());
    return id;
  }

  public Long deletePost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(ErrorStatus.POST_NOT_FOUND));
    postRepository.delete(post);
    return id;
  }
}