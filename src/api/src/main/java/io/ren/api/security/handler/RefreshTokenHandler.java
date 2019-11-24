package io.ren.api.security.handler;

import org.springframework.stereotype.Component;

import io.ren.api.domain.RefreshToken;
import io.ren.api.domain.User;

@Component
public interface RefreshTokenHandler {
    RefreshToken createRefreshToken(User user);
    boolean verifyRefreshToken(RefreshToken refreshToken);
}
