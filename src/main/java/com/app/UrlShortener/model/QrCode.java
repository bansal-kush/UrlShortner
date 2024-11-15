package com.app.UrlShortener.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "QR_CODE")
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] qrCode;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
