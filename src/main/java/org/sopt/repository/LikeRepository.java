package org.sopt.repository;

import java.util.Optional;
import org.sopt.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
  Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
