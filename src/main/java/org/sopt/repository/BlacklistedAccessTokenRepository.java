package org.sopt.repository;

import java.time.LocalDateTime;
import org.sopt.domain.BlacklistedAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedAccessTokenRepository extends JpaRepository<BlacklistedAccessToken, Long> {
  boolean existsByToken(String token);
  void deleteByExpiresAtBefore(LocalDateTime now);
}
