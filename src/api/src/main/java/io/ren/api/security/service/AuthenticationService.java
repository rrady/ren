package io.ren.api.security.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.ren.api.exception.RenException;

@Service
public interface AuthenticationService {
    void register(String email, String username, String password) throws RenException;
    String authenticate(String email, String password) throws RenException;
    void changePassword(UUID userId, String currentPassword, String newPassword) throws RenException;
}
