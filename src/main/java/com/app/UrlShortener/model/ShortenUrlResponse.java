package com.app.UrlShortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenUrlResponse {
    private String shortUrl;
    private String longUrl;
}
