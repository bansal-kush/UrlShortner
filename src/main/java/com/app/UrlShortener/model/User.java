package com.app.UrlShortener.model;

import com.app.UrlShortener.Repository.QrCodeRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "\"USER\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email(message = "Email id is Invalid")
    @NotBlank(message = "Username cannot be blank")
    @Column(unique = true, nullable = false)
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ShortenedUrl> shortenedUrls;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<QrCode> qrCodes;
}
