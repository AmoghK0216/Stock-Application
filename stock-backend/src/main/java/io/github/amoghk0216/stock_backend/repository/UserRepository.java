package io.github.amoghk0216.stock_backend.repository;

import io.github.amoghk0216.stock_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
