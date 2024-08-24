package com.app.UrlShortener.service;

import com.app.UrlShortener.Repository.UserRepository;
import com.app.UrlShortener.exception.EmailAlreadyRegisteredException;
import com.app.UrlShortener.exception.UserNotFoundException;
import com.app.UrlShortener.model.ShortenedUrl;
import com.app.UrlShortener.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<String> addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }catch(DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EmailAlreadyRegisteredException();
        }
        catch(Exception exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllUrls(String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            if(user == null) {
                throw  new UserNotFoundException();
            }
            List<ShortenedUrl> shortenedUrls = user.getShortenedUrls();
            return new ResponseEntity<>(shortenedUrls, HttpStatus.OK);
        }catch(Exception exception) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
