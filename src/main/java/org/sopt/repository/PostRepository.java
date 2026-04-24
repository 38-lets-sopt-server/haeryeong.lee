package org.sopt.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.domain.Post;

public interface PostRepository {
  Long generateId();
  Post save(Post post);
  Optional<Post> findById(Long id);
  List<Post> findAll();
  void delete(Post post);
}