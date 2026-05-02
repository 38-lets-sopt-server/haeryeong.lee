package org.sopt.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  Post save(Post post);
  Optional<Post> findById(Long id);
  List<Post> findAll();
  void delete(Post post);
}