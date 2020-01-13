package com.ren.api.service;

import com.ren.api.exceptions.RenException;
import com.ren.api.model.JsonWebToken;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    void signUp(String email, String username, String password) throws RenException;

    JsonWebToken signIn(String email, String password) throws RenException;

    void changePassword(Long userId, String currentPassword, String newPassword) throws RenException;

    JsonWebToken refresh(String refreshToken) throws RenException;
}