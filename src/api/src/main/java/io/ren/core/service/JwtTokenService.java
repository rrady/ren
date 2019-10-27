package io.ren.core.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    String getToken(UUID userId);
    Optional<UUID> getSubject(String token);
}
