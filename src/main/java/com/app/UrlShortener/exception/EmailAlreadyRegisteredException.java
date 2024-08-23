package com.app.UrlShortener.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyRegisteredException extends CustomException{
    public EmailAlreadyRegisteredException() {
        super("Email already registered", HttpStatus.BAD_REQUEST);
    }
}
