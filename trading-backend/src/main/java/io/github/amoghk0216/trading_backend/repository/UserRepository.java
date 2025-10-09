package io.github.amoghk0216.trading_backend.repository;

import io.github.amoghk0216.trading_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
