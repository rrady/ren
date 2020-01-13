package io.ren.api.security.handler;

import io.ren.api.domain.RefreshToken;
import io.ren.api.domain.User;

public interface RefreshTokenHandler {
    RefreshToken createRefreshToken(User user);
    boolean verifyRefreshToken(RefreshToken refreshToken);
}
