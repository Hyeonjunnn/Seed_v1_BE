package com.hyeonjunnn.seed_v1_be.domain.projectTech.service;

import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.repository.ProjectTechRepository;
import com.hyeonjunnn.seed_v1_be.entity.ProjectTech;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectTechServiceImpl implements ProjectTechService {
    private final ProjectTechRepository projectTechRepository;

    @Override
    public void deleteProjectTech(ProjectTechRequestDto projectTechRequestDto) {
        Long projectNo = projectTechRequestDto.getProjectNo();
        Long techNo = projectTechRequestDto.getTechNo();

        ProjectTech.ProjectTechId projectTechId = new ProjectTech.ProjectTechId(projectNo, techNo);

        if (!projectTechRepository.existsById(projectTechId)) {
            throw new RuntimeException("해당 기술 스택 정보가 존재하지 않습니다.");
        }

        projectTechRepository.deleteById(projectTechId);
    }
}
