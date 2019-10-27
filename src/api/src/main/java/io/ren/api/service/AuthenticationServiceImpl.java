package io.ren.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ren.core.domain.User;
import io.ren.core.exception.RenException;
import io.ren.core.service.AuthenticationService;
import io.ren.core.service.JwtTokenService;
import io.ren.infrastructure.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public void register(String email, String username, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new RenException(String.format("Email: '%s' is already in use.", email));
        }

        User newUser = new User(email, username, password);
        userRepository.save(newUser);
    }

    @Override
    public String authenticate(String email, String password) throws RenException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RenException("Invalid credentials.");
        }

        UUID userId = user.get().getId();
        return jwtTokenService.getToken(userId);
    }

    @Override
    public void changePassword(UUID userId, String currentPassword, String newPassword) throws RenException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RenException(String.format("User with id: '%s' was not found.", userId));
        }

        if (currentPassword != user.get().getPassword()) {
            throw new RenException("Invalid current password.");
        }

        User actualUser = user.get();
        actualUser.setPassword(newPassword);
        userRepository.save(actualUser);
    }
}
