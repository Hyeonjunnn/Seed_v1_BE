package com.hyeonjunnn.seed_v1_be.domain.role.repository;

import com.hyeonjunnn.seed_v1_be.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String user);
}
