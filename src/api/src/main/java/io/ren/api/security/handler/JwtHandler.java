package io.ren.api.security.handler;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public interface JwtHandler {
    String createToken(Long userId);
    boolean verifyToken(String token);
    Claims getClaims(String token);
}