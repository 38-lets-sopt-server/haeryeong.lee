package org.sopt.service;

import java.util.List;
import org.sopt.domain.BoardType;
import org.sopt.domain.Post;
import org.sopt.dto.request.CreatePostRequest;
import org.sopt.dto.request.UpdatePostRequest;
import org.sopt.dto.response.CreatePostResponse;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.code.status.ErrorCode;
import org.sopt.global.exception.GeneralException;
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

    Post post = new Post(id, request.getTitle(), request.getContent(), request.getAuthor(), request.isAnonymous(), request.isQuestion(), request.getBoardType());
    Post savedPost = postRepository.save(post);
    return new CreatePostResponse(savedPost.getId(), "게시글 등록 완료!");
  }

  public PostListResponse getAllPosts(int page, int size, BoardType boardType) {
    if (page < 0) {
      throw new GeneralException(ErrorCode.INVALID_PAGE_NUMBER);
    }
    if (size <= 0) {
      throw new GeneralException(ErrorCode.INVALID_PAGE_SIZE);
    }

    List<Post> posts = postRepository.findAll();

    if (boardType != null) {
      posts = posts.stream().filter(post -> post.getBoardType() == boardType).toList();
    }

    int totalElements = posts.size();
    int totalPages = (int) Math.ceil((double) totalElements / size);
    int start = page * size;
    int end = Math.min(start + size, totalElements);

    List<PostResponse> postResponses;

    if (start >= totalElements) {
      postResponses = List.of();
    } else {
      postResponses = posts.subList(start, end).stream().map(PostResponse::new).toList();
    }

    return new PostListResponse(postResponses, postResponses.size(), totalPages, totalElements, page == 0, page >= totalPages - 1);
  }

  public PostResponse getPost(Long id) {
    PostValidator.validatePostId(id);
    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);
    return new PostResponse(post);
  }

  public Long updatePost(Long id, UpdatePostRequest request) {
    PostValidator.validatePostTitle(request.getTitle());

    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);

    post.update(request.title, request.content, request.isQuestion(), request.isAnonymous());
    return id;
  }

  public Long deletePost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);
    postRepository.delete(post);
    return id;
  }
}
