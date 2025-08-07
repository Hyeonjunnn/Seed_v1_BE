package com.hyeonjunnn.seed_v1_be.domain.projectTech.repository;

import com.hyeonjunnn.seed_v1_be.entity.ProjectTech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTechRepository extends JpaRepository<ProjectTech, Long> {
    List<ProjectTech> findProjectTechesByProjectProjectNo(Long projectNo);

    boolean existsById(ProjectTech.ProjectTechId projectTechId);

    void deleteById(ProjectTech.ProjectTechId projectTechId);
}
