package com.ren.api.service;

import org.springframework.stereotype.Service;

import com.ren.api.exceptions.RenException;
import com.ren.api.model.JsonWebToken;

@Service
public interface IdentityService {
    void signUp(String email, String username, String password) throws RenException;

    JsonWebToken signIn(String email, String password) throws RenException;

    void changePassword(Long userId, String currentPassword, String newPassword) throws RenException;

    JsonWebToken refresh(String refreshToken) throws RenException;
}