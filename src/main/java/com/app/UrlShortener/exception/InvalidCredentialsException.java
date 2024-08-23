package com.app.UrlShortener.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends CustomException{
    public InvalidCredentialsException() {
        super("Invalid Credentials", HttpStatus.FORBIDDEN);
    }
}
