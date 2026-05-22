package org.sopt.service;

import java.util.List;
import org.sopt.domain.BoardType;
import org.sopt.domain.Post;
import org.sopt.domain.User;
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
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public PostService(
      PostRepository postRepository,
      UserRepository userRepository
  ) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Transactional  // 저장 → DB 변경 발생 → 트랜잭션 커밋 시 반영
  public CreatePostResponse createPost(CreatePostRequest request, Long userId) {
    PostValidator.validatePostTitle(request.title());

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

    Post post = new Post(request.title(), request.content(), user, request.isQuestion(), request.isAnonymous(), request.boardType());
    postRepository.save(post);
    return new CreatePostResponse(post.getId(), "게시글 등록 완료!");
  }

  @Transactional(readOnly = true)  // 조회 전용 → 더티 체킹 안 함 → 성능 최적화
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

  @Transactional(readOnly = true)
  public PostResponse getPost(Long id) {
    PostValidator.validatePostId(id);
    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);
    return new PostResponse(post);
  }

  @Transactional  // 변경 → 더티 체킹으로 save() 없이 자동 UPDATE
  public PostResponse updatePost(Long id, UpdatePostRequest request, Long userId) {
    PostValidator.validatePostTitle(request.title());

    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);

    if (!post.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.FORBIDDEN);
    }

    post.update(request.title(), request.content(), request.isQuestion(), request.isAnonymous()); // save() 호출 없어도 트랜잭션 커밋 시 UPDATE 쿼리 자동 실행
    return new PostResponse(post);
  }

  // DELETE 📝 과제
  @Transactional
  public PostResponse deletePost(Long id, Long userId) {
    Post post = postRepository.findById(id)
        .orElseThrow(PostNotFoundException::new);

    if (!post.getUser().getId().equals(userId)) {
      throw new GeneralException(ErrorCode.FORBIDDEN);
    }

    PostResponse response = new PostResponse(post);
    postRepository.delete(post);
    return response;
  }
}
