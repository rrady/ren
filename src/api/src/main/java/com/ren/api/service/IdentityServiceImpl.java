package com.ren.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ren.api.domain.entities.RefreshToken;
import com.ren.api.domain.entities.User;
import com.ren.api.domain.exceptions.RenException;
import com.ren.api.model.JsonWebToken;
import com.ren.api.repository.RefreshTokenRepository;
import com.ren.api.repository.UserRepository;
import com.ren.api.security.AccessTokenProvider;
import com.ren.api.security.RefreshTokenProvider;

@Service
public class IdentityServiceImpl implements IdentityService {

    private UserRepository userRepository;
    private RefreshTokenRepository refreshTokenRepository;
    private AccessTokenProvider accessTokenProvider;
    private RefreshTokenProvider refreshTokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public IdentityServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository,
        AccessTokenProvider accessTokenProvider, RefreshTokenProvider refreshTokenProvider, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenProvider = accessTokenProvider;
        this.refreshTokenProvider = refreshTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(String email, String username, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new RenException(String.format("Email: '%s' is already in use.", email));
        }

        String passwordHashed = passwordEncoder.encode(password);
        User newUser = new User(email, username, passwordHashed);
        userRepository.save(newUser);
    }

    @Override
    public JsonWebToken signIn(String email, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RenException("Invalid credentials.");
        }

        User actualUser = user.get();
        if (!passwordEncoder.matches(password, actualUser.getPassword())) {
            throw new RenException("Invalid credentials.");
        }

        Long userId = actualUser.getId();
        String accessToken = accessTokenProvider.createToken(userId);
        RefreshToken refreshToken = refreshTokenProvider.createRefreshToken(actualUser);
        refreshTokenRepository.save(refreshToken);
        return new JsonWebToken(accessToken, refreshToken.getToken());
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) throws RenException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RenException(String.format("User with id: '%s' was not found.", userId));
        }

        User actualUser = user.get();
        if (!passwordEncoder.matches(currentPassword, actualUser.getPassword())) {
            throw new RenException("Invalid credentials.");
        }

        String newPasswordHashed = passwordEncoder.encode(newPassword);
        actualUser.setPassword(newPasswordHashed);
        userRepository.save(actualUser);
    }

    @Override
    public JsonWebToken refresh(String refreshToken) throws RenException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if (optionalRefreshToken.isEmpty()) {
            throw new RenException("Refresh token was not found.");
        }

        RefreshToken actualRefreshToken = optionalRefreshToken.get();
        if (!refreshTokenProvider.validateRefreshToken(actualRefreshToken)) {
            throw new RenException("Refresh token is expired.");
        }

        User user = actualRefreshToken.getUser();
        String accessToken = accessTokenProvider.createToken(user.getId());
        RefreshToken newRefreshToken = refreshTokenProvider.createRefreshToken(user);
        return new JsonWebToken(accessToken, newRefreshToken.getToken());
    }
}