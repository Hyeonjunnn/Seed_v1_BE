package com.hyeonjunnn.seed_v1_be.domain.projectLink.repository;

import com.hyeonjunnn.seed_v1_be.entity.ProjectLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectLinkRepository extends JpaRepository<ProjectLink, Long> {
    List<ProjectLink> findProjectLinksByProjectProjectNo(Long projectNo);
}
