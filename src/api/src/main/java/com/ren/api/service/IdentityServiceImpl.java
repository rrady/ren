package com.ren.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ren.api.domain.RefreshToken;
import com.ren.api.domain.User;
import com.ren.api.exceptions.Codes;
import com.ren.api.exceptions.RenException;
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
            throw new RenException(Codes.RESOURCE_ALREADY_EXISTS, String.format("Email: '%s' is already in use.", email));
        }

        String passwordHashed = passwordEncoder.encode(password);
        User newUser = new User(email, username, passwordHashed);
        userRepository.save(newUser);
    }

    @Override
    public JsonWebToken signIn(String email, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new RenException(Codes.INVALID_INPUT, "Invalid email.");
        }

        User actualUser = user.get();
        if (!passwordEncoder.matches(password, actualUser.getPassword())) {
            throw new RenException(Codes.INVALID_INPUT, "Invalid password.");
        }

        String accessToken = accessTokenProvider.createToken(actualUser.getId(), actualUser.getName(), actualUser.getEmail());
        RefreshToken refreshToken = refreshTokenProvider.createRefreshToken(actualUser);
        refreshTokenRepository.save(refreshToken);
        return new JsonWebToken(accessToken, refreshToken.getToken());
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) throws RenException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("User with id: '%s' was not found.", userId));
        }

        User actualUser = user.get();
        if (!passwordEncoder.matches(currentPassword, actualUser.getPassword())) {
            throw new RenException(Codes.INVALID_INPUT, "Invalid current password.");
        }

        String newPasswordHashed = passwordEncoder.encode(newPassword);
        actualUser.setPassword(newPasswordHashed);
        userRepository.save(actualUser);
    }

    @Override
    public JsonWebToken refresh(String refreshToken) throws RenException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if (!optionalRefreshToken.isPresent()) {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, "Refresh token was not found.");
        }

        RefreshToken actualRefreshToken = optionalRefreshToken.get();
        if (!refreshTokenProvider.validateRefreshToken(actualRefreshToken)) {
            throw new RenException(Codes.RESOURCE_EXPIRED, "Refresh token is expired.");
        }

        User user = actualRefreshToken.getUser();
        String accessToken = accessTokenProvider.createToken(user.getId(), user.getName(), user.getEmail());
        RefreshToken newRefreshToken = refreshTokenProvider.createRefreshToken(user);
        refreshTokenRepository.save(newRefreshToken);
        return new JsonWebToken(accessToken, newRefreshToken.getToken());
    }
}