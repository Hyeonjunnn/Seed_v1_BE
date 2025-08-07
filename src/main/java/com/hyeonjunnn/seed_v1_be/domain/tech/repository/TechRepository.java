package com.hyeonjunnn.seed_v1_be.domain.tech.repository;

import com.hyeonjunnn.seed_v1_be.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<Tech, Long> {
}
