package io.ren.api.security.handler;

import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHandlerImpl implements JwtHandler {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiryMinutes}")
    private int expiryMinutes;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Override
    public String createToken(Long userId) {
        SecretKey secretKey = getSecreyKey();

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuer(issuer)
                .setAudience(audience)
                .setExpiration(expireTimeFromNow())
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Claims claims = getClaims(token);

            if (!claims.getIssuer().equals(issuer) || !claims.getAudience().equals(audience)) {
                return false;
            }

            return true;
        } catch (SignatureException e) {
            // logging "Invalid JWT signature."
        } catch (MalformedJwtException e) {
            // logging "Invalid JWT token."
        } catch (ExpiredJwtException e) {
            // logging "Expired JWT token."
        } catch (UnsupportedJwtException e) {
            // logging "Unsupported JWT token."
        } catch (IllegalArgumentException e) {
            // logging "JWT claims string is empty."
        }

        return false;
    }

    @Override
    public Claims getClaims(String token) {
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
