package com.example.reservationudemy.security;

import com.example.reservationudemy.models.ReservationApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecretKey;

    @Value("${app-jwt-expiration-milliseconds}")
    private Long expiration;

    // 🔐 Generate JWT
    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(userName)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key()) // algorithm inferred automatically
                .compact();
    }

    // 👤 Extract username
    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new ReservationApiException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid or Expired JWT Token"
            );
        }
    }

    // 🔑 Secret Key
    private SecretKey key() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }
}
