package com.app.UrlShortener.service;

import com.app.UrlShortener.Repository.QrCodeRepository;
import com.app.UrlShortener.Repository.UserRepository;
import com.app.UrlShortener.exception.QrCodeCreationException;
import com.app.UrlShortener.exception.UserNotFoundException;
import com.app.UrlShortener.model.QrCode;
import com.app.UrlShortener.model.QrCodeResponse;
import com.app.UrlShortener.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

@Service
public class QrCodeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    QrCodeRepository qrCodeRepository;
    public ResponseEntity<?> createQrCode(String username, String url) {

            User user = userRepository.findUserByUsername(username);
            if(user == null) {
                throw new UserNotFoundException();
            }

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try{
                BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE,300, 300);
                ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
                MatrixToImageConfig con = new MatrixToImageConfig( 0xFFFFFFFF , 0x00000000 ) ;

                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
                byte[] pngData = pngOutputStream.toByteArray();
                List<QrCode> qrCodes=user.getQrCodes();
                QrCode qrCode = new QrCode();
                qrCode.setUser(user);
                qrCode.setQrCode(pngData);
                qrCodes.add(qrCode);
                userRepository.save(user);
                return new ResponseEntity<>(new QrCodeResponse(pngData), HttpStatus.OK);

            }catch (Exception exception){
                throw new QrCodeCreationException();
            }
    }
}
