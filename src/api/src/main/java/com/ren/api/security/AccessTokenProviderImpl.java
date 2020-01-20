package com.ren.api.security;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class AccessTokenProviderImpl implements AccessTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiryMinutes}")
    private int expiryMinutes;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Override
    public String createToken(Long userId, String username, String email) {
        SecretKey secretKey = getSecreyKey();
        Map<String, Object> additionalClaims = new HashMap<>();
        additionalClaims.put("username", username);
        additionalClaims.put("email", email);     

        return Jwts.builder()
            .setSubject(userId.toString())
            .addClaims(additionalClaims)
            .setIssuer(issuer)
            .setAudience(audience)
            .setExpiration(expireTimeFromNow())
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = (Claims)getClaims(token);

            if (!claims.getIssuer().equals(issuer) || !claims.getAudience().equals(audience)) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // logging "Invalid JWT signature."
        }

        return false;
    }

    @Override
    public Map<String, Object> getClaims(String token) {
        SecretKey secretKey = getSecreyKey();

        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    private SecretKey getSecreyKey() {
        byte[] secret = secretKey.getBytes();
        SecretKey secretKey = Keys.hmacShaKeyFor(secret);

        return secretKey;
    }

    private Date expireTimeFromNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryMinutes);
        return calendar.getTime();
    }
}