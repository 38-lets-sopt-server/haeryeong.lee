package org.sopt.repository;

import java.util.Optional;
import org.sopt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);  // 메서드 이름으로 쿼리 자동 생성
}