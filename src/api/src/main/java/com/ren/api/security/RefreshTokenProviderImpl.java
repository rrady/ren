package com.ren.api.security;

import com.ren.api.domain.RefreshToken;
import com.ren.api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class RefreshTokenProviderImpl implements RefreshTokenProvider {

    @Value("${jwt.refreshTokenExpiryDays}")
    private int refreshTokenExpiryDays;

    @Override
    public RefreshToken createRefreshToken(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        return new RefreshToken(user, token);
    }

    @Override
    public boolean validateRefreshToken(RefreshToken refreshToken) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, refreshTokenExpiryDays);
        Date shouldExpireDate = calendar.getTime();

        if(refreshToken.getCreatedAt().before(shouldExpireDate)) {
            return false;
        }

        return true;
    }
}