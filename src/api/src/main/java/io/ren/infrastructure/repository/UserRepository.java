package io.ren.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.ren.core.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
