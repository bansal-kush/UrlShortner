package com.app.UrlShortener.config;

import com.app.UrlShortener.exception.*;
import com.app.UrlShortener.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Error errorResponse = new Error();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            errorResponse.addErrorMessage(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomUrlAlreadyExistsException.class)
    public ResponseEntity<Error> handleCustomUrlAlreadyExistsException(CustomUrlAlreadyExistsException exception) {
        return exception.sendResponse();
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException exception) {
        return exception.sendResponse();
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Error> handleInvalidTokeException(InvalidTokenException exception) {
        return exception.sendResponse();
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Error> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        return exception.sendResponse();
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Error> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception) {
        return exception.sendResponse();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Error> handleHttpServerErrorException(HttpServerErrorException exception) {
        Error error = new Error();
        error.setMessage("Internal server error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
