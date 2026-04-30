package org.sopt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements PostRepository {
  private final List<Post> postList = new ArrayList<>();
  private Long nextId = 1L;

  @Override
  public Long generateId() {
    return nextId++;
  }

  @Override
  public Post save(Post post) {
    postList.add(post);
    return post;
  }

  @Override
  public Optional<Post> findById(Long id) {
    return postList.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst();
  }

  @Override
  public List<Post> findAll() {
    return postList;
  }

  @Override
  public void delete(Post post) {
    postList.remove(post);
  }
}