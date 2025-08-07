package com.hyeonjunnn.seed_v1_be.domain.status.repository;

import com.hyeonjunnn.seed_v1_be.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, String> {
}
