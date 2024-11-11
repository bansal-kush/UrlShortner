package com.app.UrlShortener.service;

import com.app.UrlShortener.model.ShortenedUrl;
import org.assertj.core.error.ShouldEndWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlCacheService {
    @Autowired
    private RedisTemplate<String, ShortenedUrl> redisTemplate;
    private final String CACHE_PREFIX = "shortUrl:";

    public ShortenedUrl getCachedUrl(String shortUrl) {
        return redisTemplate.opsForValue().get(CACHE_PREFIX + shortUrl);
    }

    public void cacheUrl(String shortUrl, ShortenedUrl originalUrl) {
        redisTemplate.opsForValue().set(CACHE_PREFIX + shortUrl, originalUrl);
    }
}
