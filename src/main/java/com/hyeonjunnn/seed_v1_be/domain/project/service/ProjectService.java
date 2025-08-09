package com.hyeonjunnn.seed_v1_be.domain.project.service;

import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    void saveProject(User user, ProjectRequestDto projectRequestDto);

    Page<ProjectResponseDto> getProjects(User user, Pageable pageable);

    ProjectResponseDto getProject(Long projectNo);

    void updateProject(User user, Long projectNo, ProjectRequestDto projectRequestDto);

    void deleteProject(User user, Long projectNo);
}
