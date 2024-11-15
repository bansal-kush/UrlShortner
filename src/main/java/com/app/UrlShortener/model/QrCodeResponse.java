package com.app.UrlShortener.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class QrCodeResponse {
    private byte[] qrCode;
}
