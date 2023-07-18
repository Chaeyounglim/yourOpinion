package com.example.youropinion.repository;

import com.example.youropinion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String inputUsername);
    Optional<User> findByEmail(String email);
}
