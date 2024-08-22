package com.app.UrlShortener.service;

import com.app.UrlShortener.Repository.UrlsRepository;
import com.app.UrlShortener.Repository.UserRepository;
import com.app.UrlShortener.model.ShortenUrlRequest;
import com.app.UrlShortener.model.ShortenUrlResponse;
import com.app.UrlShortener.model.ShortenedUrl;
import com.app.UrlShortener.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    @Autowired
    private UrlsRepository urlsRepository;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<?> createShortenedUrl(String username, ShortenUrlRequest shortenUrlRequest) {
        try{
            ShortenedUrl shortenedUrl = new ShortenedUrl();
            shortenedUrl.setShortUrl("shortened url");
            shortenedUrl.setLongUrl(shortenUrlRequest.getLongUrl());
            User user = userRepository.findUserByUsername(username);
            shortenedUrl.setUser(user);
            List<ShortenedUrl> shortenedUrls = user.getShortenedUrls();
            shortenedUrls.add(shortenedUrl);
            userRepository.save(user);
            ShortenUrlResponse shortenUrlResponse = new ShortenUrlResponse();
            shortenUrlResponse.setShortUrl(shortenedUrl.getShortUrl());
            shortenUrlResponse.setLongUrl(shortenedUrl.getLongUrl());

            return  new ResponseEntity<>(shortenUrlResponse, HttpStatus.OK);
        }catch(Exception exception) {
            return new ResponseEntity<>("Failed to shorten Url " +exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
