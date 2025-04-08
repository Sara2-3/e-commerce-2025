package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.Role;
import com.example.sara.loginregistera.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByRole(Role role);

    Optional<User> findByEmail(String email);
}