package com.app.UrlShortener.controller;

import com.app.UrlShortener.model.QrCodeRequest;
import com.app.UrlShortener.service.QrCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/qr")
@CrossOrigin("http://localhost:3000")
public class QrCodeController {
    @Autowired
    QrCodeService qrCodeService;
    @PostMapping(value =  "/create")
    public ResponseEntity<?> createQrCode(@AuthenticationPrincipal UserDetails userDetails,@RequestBody QrCodeRequest qrCodeRequest){
        return qrCodeService.createQrCode(userDetails.getUsername(), qrCodeRequest.getUrl());
    }
}
