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
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Random;

@Service
public class UrlService {
    @Autowired
    private UrlsRepository urlsRepository;
    @Autowired
    private UserRepository userRepository;

    private static final int NUM_CHARS_SHORT_LINK = 7;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static  final String BASE_URL = "http://localhost:8080/";

    private final Random random = new Random();

    public ResponseEntity<?> createShortenedUrl(String username, ShortenUrlRequest shortenUrlRequest) {
        try{
            ShortenedUrl shortenedUrl = new ShortenedUrl();
            shortenedUrl.setLongUrl(shortenUrlRequest.getLongUrl());
            shortenedUrl.setShortUrl(BASE_URL + generateRandomShortUrl());
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

    public boolean checkShortLinkExists(String shortUrl) {
        try{
            ShortenedUrl shortenedUrl = urlsRepository.findByShortUrl(shortUrl);
            return shortenedUrl != null;
        }catch (Exception exception){
            return true;
        }
    }

    public String generateRandomShortUrl() {
        char[] result = new char[NUM_CHARS_SHORT_LINK];
        while (true) {
            for (int i = 0; i < NUM_CHARS_SHORT_LINK; i++) {
                int randomIndex = random.nextInt(ALPHABET.length() - 1);
                result[i] = ALPHABET.charAt(randomIndex);
            }
            String shortUrl = new String(result);
            if (!checkShortLinkExists(shortUrl)) {
                return shortUrl;
            }
        }
    }

    public RedirectView visitUrl(String shortUrl) {
        try{
            String completeShortUrl = BASE_URL + shortUrl;
            RedirectView redirectView = new RedirectView();
            ShortenedUrl shortenedUrl = urlsRepository.findByShortUrl(completeShortUrl);
            redirectView.setUrl(shortenedUrl.getLongUrl());
            return redirectView;
        }catch (Exception exception) {
            System.out.println("Invalid url hit");
            return new RedirectView();
        }
    }
}

