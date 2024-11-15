package com.app.UrlShortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
public class QrCodeCreationException extends CustomException{
    public QrCodeCreationException() {
        super("Error while creating QR Code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
