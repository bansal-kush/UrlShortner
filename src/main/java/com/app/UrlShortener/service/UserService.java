package com.app.UrlShortener.service;

import com.app.UrlShortener.Repository.UserRepository;
import com.app.UrlShortener.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            return new ResponseEntity<>("Email already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception exception) {

            return new ResponseEntity<>("Failed to Create User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
