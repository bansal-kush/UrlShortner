package com.app.UrlShortener.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShortenUrlRequest {
    @URL(message = "Not a valid url")
    private String longUrl;

    private String customUrl;

}
