package com.app.UrlShortener.controller;


import com.app.UrlShortener.model.ShortenUrlRequest;
import com.app.UrlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    @Autowired
    UrlService urlService;
    @PostMapping("/create")
    private ResponseEntity<?> createShortenedUrl(@AuthenticationPrincipal UserDetails userDetails,  @RequestBody ShortenUrlRequest shortenUrlRequest) {
        return urlService.createShortenedUrl(userDetails.getUsername(), shortenUrlRequest);
    }

}
