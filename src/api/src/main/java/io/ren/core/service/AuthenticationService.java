package io.ren.core.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.ren.core.exception.RenException;

@Service
public interface AuthenticationService {
    void register(String email, String username, String password) throws RenException;
    String authenticate(String email, String password) throws RenException;
    void changePassword(UUID userId, String currentPassword, String newPassword) throws RenException;
}
