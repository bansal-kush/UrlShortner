package com.app.UrlShortener.Repository;

import com.app.UrlShortener.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
}
