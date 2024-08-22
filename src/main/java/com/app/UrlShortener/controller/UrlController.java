package com.app.UrlShortener.controller;


import com.app.UrlShortener.model.ShortenUrlRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    @PostMapping("/create")
    private ResponseEntity<?> createShortenedUrl(@RequestBody ShortenUrlRequest shortenUrlRequest) {
        return ResponseEntity.ok("Shortened Url");
    }

}
