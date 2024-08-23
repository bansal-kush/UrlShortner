package com.app.UrlShortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public  ResponseEntity<Error> sendResponse(){
        Error error = new Error();
        error.setMessage(this.message);
        return new ResponseEntity<>(error, this.httpStatus);
    }
}
