package com.app.UrlShortener.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "SHORTENED_URL")
public class ShortenedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shortUrl;
    private String longUrl;
    @JsonBackReference
    @ManyToOne
    private User user;
}
