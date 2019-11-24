package io.ren.api.security.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.ren.api.domain.User;
import io.ren.api.exception.RenException;
import io.ren.api.repository.UserRepository;
import io.ren.api.dto.JsonWebToken;
import io.ren.api.domain.RefreshToken;
import io.ren.api.security.handler.JwtHandler;
import io.ren.api.security.handler.RefreshTokenHandler;
import io.ren.api.repository.RefreshTokenRepository;

@Service
public class IdentityServiceImpl implements IdentityService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtHandler jwtHandler;

    @Autowired
    private RefreshTokenHandler refreshTokenHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(String email, String username, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new RenException(String.format("Email: '%s' is already in use.", email));
        }

        String passwordHashed = passwordEncoder().encode(password);
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
        if (!passwordEncoder().matches(password, actualUser.getPassword())) {
            throw new RenException("Invalid credentials.");
        }

        Long userId = actualUser.getId();
        String accessToken = jwtHandler.createToken(userId);
        RefreshToken refreshToken = refreshTokenHandler.createRefreshToken(actualUser);
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
        if (!passwordEncoder().matches(currentPassword, actualUser.getPassword())) {
            throw new RenException("Invalid credentials.");
        }

        String newPasswordHashed = passwordEncoder().encode(newPassword);
        actualUser.setPassword(newPasswordHashed);
        userRepository.save(actualUser);
    }

    @Override
    public JsonWebToken refresh(String token) throws RenException {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByToken(token);
        if (token.isEmpty()) {
            throw new RenException("Refresh token was not found.");
        }

        RefreshToken actualRefreshToken = optionalRefreshToken.get();
        if (!refreshTokenHandler.verifyRefreshToken(actualRefreshToken)) {
            throw new RenException("Refresh token is expired.");
        }

        User user = actualRefreshToken.getUser();
        String accessToken = jwtHandler.createToken(user.getId());
        RefreshToken refreshToken = refreshTokenHandler.createRefreshToken(user);
        return new JsonWebToken(accessToken, refreshToken.getToken());
    }
}
