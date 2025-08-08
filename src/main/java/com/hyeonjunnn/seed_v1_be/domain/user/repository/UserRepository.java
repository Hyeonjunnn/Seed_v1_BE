package com.hyeonjunnn.seed_v1_be.domain.user.repository;

import com.hyeonjunnn.seed_v1_be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findUsersByEmailContains(Pageable pageable, String email);
}
