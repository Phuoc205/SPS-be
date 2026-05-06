package com.smartparking.auth.repository;

import com.smartparking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}