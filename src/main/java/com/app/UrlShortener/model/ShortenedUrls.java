package com.app.UrlShortener.model;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenedUrls {
    private Integer id;
    private String shortUrl;
    private String longUrl;
    @ManyToOne
    private User user;
}
