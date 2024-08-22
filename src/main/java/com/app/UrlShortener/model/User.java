package com.app.UrlShortener.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ShortenedUrls> shortenedUrls;

}
