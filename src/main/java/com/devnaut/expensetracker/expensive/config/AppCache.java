package com.devnaut.expensetracker.expensive.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppCache {
  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("images", "amount", "expenses");
  }
}
