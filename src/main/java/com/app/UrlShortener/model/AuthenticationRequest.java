package com.app.UrlShortener.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;

}
