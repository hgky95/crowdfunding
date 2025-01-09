package com.crowdfunding.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crowdfunding.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByAddress(String address);
    Optional<User> findById(Long id);
} 
