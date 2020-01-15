package com.ren.api.security;

import com.ren.api.domain.RefreshToken;
import com.ren.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenProvider {
    RefreshToken createRefreshToken(User user);
    boolean validateRefreshToken(RefreshToken refreshToken);
}