package com.app.UrlShortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends CustomException{
    public InvalidTokenException() {
        super("Invalid Jwt Token", HttpStatus.UNAUTHORIZED);
    }
}
