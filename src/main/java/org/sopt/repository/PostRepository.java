package org.sopt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
  private final List<Post> postList = new ArrayList<>();
  private Long nextId = 1L;

  public Long generateId() {
    return nextId++;
  }

  public Post save(Post post) {
    postList.add(post);
    return post;
  }

  public Optional<Post> findById(Long id) {
    return postList.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst();
  }

  public List<Post> findAll() {
    return postList;
  }

  public void delete(Post post) {
    postList.remove(post);
  }
}