package org.sopt.config;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.sopt.repository.BlacklistedAccessTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BlacklistedTokenCleanupScheduler {

  private final BlacklistedAccessTokenRepository blacklistedAccessTokenRepository;

  @Scheduled(cron = "0 0 0 * * *")
  @Transactional
  public void deletedExpiredTokens() {
    blacklistedAccessTokenRepository.deleteByExpiresAtBefore(LocalDateTime.now());
  }

}
