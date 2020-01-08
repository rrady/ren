package io.ren.api.security.service;

import io.ren.api.dto.JsonWebToken;
import io.ren.api.exception.RenException;

public interface IdentityService {
    void signUp(String email, String username, String password) throws RenException;
    JsonWebToken signIn(String email, String password) throws RenException;
    void changePassword(Long userId, String currentPassword, String newPassword) throws RenException;
    JsonWebToken refresh(String refreshToken) throws RenException;
}