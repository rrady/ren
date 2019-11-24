package io.ren.api.security.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.ren.api.domain.RefreshToken;
import io.ren.api.domain.User;

@Component
public class RefreshTokenHandlerImpl implements RefreshTokenHandler {
    @Value("${jwt.refreshTokenExpiryDays}")
    private int refreshTokenExpiryDays;

    @Override
    public RefreshToken createRefreshToken(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        return new RefreshToken(user, token);
    }

    @Override
    public boolean verifyRefreshToken(RefreshToken refreshToken) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, refreshTokenExpiryDays);
        Date shouldExpireDate = calendar.getTime();

        if(refreshToken.getCreatedAt().before(shouldExpireDate)) {
            return false;
        }

        return true;
    }
}
