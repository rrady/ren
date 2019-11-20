package io.ren.api.security.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    String getToken(Long userId);
    Optional<UUID> getSubject(String token);
}
