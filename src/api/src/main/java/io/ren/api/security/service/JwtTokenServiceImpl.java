package io.ren.api.security.service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiryMinutes}")
    private int expiryMinutes;

    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public String getToken(Long userId) {
        SecretKey key = getSecreyKey();

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuer(issuer)
                .setExpiration(expireTimeFromNow())
                .signWith(key)
                .compact();
    }

    @Override
    public Optional<UUID> getSubject(String token) {
        if (token == null || token.isEmpty()) {
            return Optional.empty();
        }

        try {
            SecretKey key = getSecreyKey();
            String subject = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return Optional.ofNullable(UUID.fromString(subject));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private SecretKey getSecreyKey() {
        byte[] secret = secretKey.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(secret);

        return key;
    }

    private Date expireTimeFromNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryMinutes);
        return calendar.getTime();
    }
}
