package com.project.back_end.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    private final String secret = "MySuperSecretKeyForJWTGeneration12345"; // ideally store in application.properties

    private final long expirationMillis = 24 * 60 * 60 * 1000; // 24 hours

    /**
     * Generate a JWT token containing the user's email
     *
     * @param email User's email
     * @return JWT token string
     */
    public String generateToken(String email) {
        SecretKey key = getSigningKey();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Get the signing key for JWT using HMAC SHA256
     *
     * @return SecretKey
     */
    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Optional: Validate a token (returns true if valid, false otherwise)
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
