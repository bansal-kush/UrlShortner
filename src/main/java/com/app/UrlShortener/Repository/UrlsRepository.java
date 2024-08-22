package com.app.UrlShortener.Repository;

import com.app.UrlShortener.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlsRepository extends JpaRepository<ShortenedUrl, Integer> {
    public ShortenedUrl findByShortUrl(String shortUrl);
}
