package com.smartparking.user.repository;

import com.smartparking.user.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByCardId(String cardId);

    @Query("""
        SELECT u FROM User u
        WHERE u.name LIKE %:keyword%
           OR u.cardId LIKE %:keyword%
           OR u.username LIKE %:keyword%
    """)
    List<User> search(@Param("keyword") String keyword);

    long countByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("""
        SELECT COUNT(u)
        FROM User u
    """)
    long countActiveUsers();
}