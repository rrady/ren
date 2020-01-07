package io.ren.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.ren.api.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
