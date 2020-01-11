package com.ren.api.security;

import org.springframework.stereotype.Component;

import com.ren.api.domain.entities.RefreshToken;
import com.ren.api.domain.entities.User;

@Component
public interface RefreshTokenProvider {
    RefreshToken createRefreshToken(User user);
    boolean validateRefreshToken(RefreshToken refreshToken);
}