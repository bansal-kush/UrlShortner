package com.app.UrlShortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends CustomException{
    public UserNotFoundException() {
        super("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
