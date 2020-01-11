package com.ren.api.security;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface AccessTokenProvider {
    String createToken(Long userId);
    boolean validateToken(String token);
    Map<String, Object> getClaims(String token);
}