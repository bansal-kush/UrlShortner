package com.app.UrlShortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrCodeRequest {
    @URL(message = "Not a valid url")
    private String url;
}
