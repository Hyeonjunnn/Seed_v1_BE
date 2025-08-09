package com.hyeonjunnn.seed_v1_be.domain.project.repository;

import com.hyeonjunnn.seed_v1_be.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findProjectsByIsVisibleTrue(Pageable pageable);
}
