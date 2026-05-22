package org.sopt.service;

import org.sopt.domain.Like;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.response.LikeResponse;
import org.sopt.global.code.status.ErrorCode;
import org.sopt.global.exception.GeneralException;
import org.sopt.global.exception.PostNotFoundException;
import org.sopt.repository.LikeRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

  private final LikeRepository likeRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
    this.likeRepository = likeRepository;
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public LikeResponse toggleLike(Long postId, Long userId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(PostNotFoundException::new);

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));

    Like like = likeRepository.findByUserIdAndPostId(userId, postId)
        .orElseGet(() -> likeRepository.save(Like.builder()
            .user(user)
            .post(post)
            .build()));

    if (like.getId() != null) {
      like.toggle();
    }

    return new LikeResponse(like.isLiked());
  }
}
