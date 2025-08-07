package com.hyeonjunnn.seed_v1_be.domain.project.service;

import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.project.dto.ProjectResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.project.repository.ProjectRepository;
import com.hyeonjunnn.seed_v1_be.domain.projectLink.dto.ProjectLinkResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.projectLink.repository.ProjectLinkRepository;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.repository.ProjectTechRepository;
import com.hyeonjunnn.seed_v1_be.domain.tech.repository.TechRepository;
import com.hyeonjunnn.seed_v1_be.entity.Project;
import com.hyeonjunnn.seed_v1_be.entity.ProjectLink;
import com.hyeonjunnn.seed_v1_be.entity.ProjectTech;
import com.hyeonjunnn.seed_v1_be.entity.Status;
import com.hyeonjunnn.seed_v1_be.entity.Tech;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectLinkRepository projectLinkRepository;
    private final ProjectTechRepository projectTechRepository;
    private final TechRepository techRepository;

    @Override
    public void saveProject(User user, ProjectRequestDto projectRequestDto) {
        Status defaultProjectStatus = Status.builder().statusCode("PRJ-003").build();

        Project project = Project.builder()
                .type(projectRequestDto.getType())
                .consistOf(projectRequestDto.getConsistOf())
                .job(projectRequestDto.getJob())
                .feature(projectRequestDto.getFeature())
                .summary(projectRequestDto.getSummary())
                .detail(projectRequestDto.getDetail())
                .startedAt(projectRequestDto.getStartedAt())
                .endedAt(projectRequestDto.getEndedAt())
                .status(defaultProjectStatus)
                .user(user)
                .build();

        Project savedProject = projectRepository.save(project);

        Status defaultLinkStatus = Status.builder().statusCode("LINK-001").build();

        projectRequestDto.getProjectLinkRequestDtos()
                .forEach(projectLinkRequestDto -> {
                    ProjectLink projectLink = ProjectLink.builder()
                            .title(projectLinkRequestDto.getTitle())
                            .link(projectLinkRequestDto.getLink())
                            .project(savedProject)
                            .status(defaultLinkStatus)
                            .build();

                    projectLinkRepository.save(projectLink);
                });

        projectRequestDto.getProjectTechRequestDtos()
                .forEach(projectTechRequestDto -> {
                    Tech tech = techRepository.findById(projectTechRequestDto.getTechNo())
                            .orElseThrow(() -> new RuntimeException("존재하지 않는 기술입니다."));

                    ProjectTech projectTech = ProjectTech.builder()
                            .id(new ProjectTech.ProjectTechId(
                                    savedProject.getProjectNo()
                                    , tech.getTechNo()
                            ))
                            .project(savedProject)
                            .tech(tech)
                            .build();

                    projectTechRepository.save(projectTech);
                });
    }

    @Override
    public Page<ProjectResponseDto> getProjects(User user, Pageable pageable) {
        Page<Project> projects;

        if (user.getRole().getName().equals("ADMIN")) {
            projects = projectRepository.findAll(pageable);
        } else {
            projects = projectRepository.findProjectsByIsVisibleTrue(pageable);
        }

        Page<ProjectResponseDto> projectResponseDtos = projects.map(project -> {
            ProjectResponseDto projectResponseDto = new ProjectResponseDto(project);

            List<ProjectTechResponseDto> projectTechResponseDtos =
                    projectTechRepository.findProjectTechesByProjectProjectNo(project.getProjectNo())
                            .stream()
                            .map(ProjectTechResponseDto::new)
                            .collect(Collectors.toList());

            projectResponseDto.setProjectTechResponseDtos(projectTechResponseDtos);

            return projectResponseDto;
        });

        return projectResponseDtos;
    }

    @Override
    public ProjectResponseDto getProject(Long projectNo) {
        ProjectResponseDto projectResponseDto = projectRepository.findById(projectNo)
                .map(ProjectResponseDto::new)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 프로젝트입니다."));

        List<ProjectLinkResponseDto> projectLinkResponseDtos =
                projectLinkRepository.findProjectLinksByProjectProjectNo(projectNo)
                        .stream().map(ProjectLinkResponseDto::new)
                        .collect(Collectors.toList());

        projectResponseDto.setProjectLinkResponseDtos(projectLinkResponseDtos);

        List<ProjectTechResponseDto> projectTechResponseDtos =
                projectTechRepository.findProjectTechesByProjectProjectNo(projectNo)
                        .stream().map(ProjectTechResponseDto::new)
                        .collect(Collectors.toList());

        projectResponseDto.setProjectTechResponseDtos(projectTechResponseDtos);

        return projectResponseDto;
    }

    @Override
    @Transactional
    public void updateProject(User user, Long projectNo, ProjectRequestDto projectRequestDto) {
        Project project = projectRepository.findById(projectNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 프로젝트입니다."));

        if (!user.getUserNo().equals(project.getUser().getUserNo())) {
            throw new RuntimeException("프로젝트 생성자가 아닙니다.");
        }

        project.setType(projectRequestDto.getType());
        project.setConsistOf(projectRequestDto.getConsistOf());
        project.setJob(projectRequestDto.getJob());
        project.setFeature(projectRequestDto.getFeature());
        project.setSummary(projectRequestDto.getSummary());
        project.setDetail(projectRequestDto.getDetail());
        project.setStartedAt(projectRequestDto.getStartedAt());
        project.setEndedAt(projectRequestDto.getEndedAt());
        project.setStatus(Status.builder().statusCode(projectRequestDto.getStatusCode()).build());

        projectRequestDto.getProjectLinkRequestDtos()
                .forEach(projectLinkRequestDto -> {
                    ProjectLink projectLink
                            = projectLinkRepository.findById(projectLinkRequestDto.getProjectLinkNo())
                            .orElseThrow(() -> new RuntimeException("존재하지 않는 링크입니다."));

                    Status updateLinkStatus = Status.builder()
                            .statusCode(projectLinkRequestDto.getStatusCode())
                            .build();

                    projectLink.setTitle(projectLinkRequestDto.getTitle());
                    projectLink.setLink(projectLinkRequestDto.getLink());
                    projectLink.setStatus(updateLinkStatus);
                });
    }

    @Override
    public void deleteProject(User user, Long projectNo) {
        Project project = projectRepository.findById(projectNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 프로젝트입니다."));

        if (!user.getUserNo().equals(project.getUser().getUserNo())) {
            throw new RuntimeException("프로젝트 생성자가 아닙니다.");
        }

        projectRepository.deleteById(projectNo);
        //TODO: 프로젝트 삭제되면 프로젝트 기술 모두 삭제 가능하도록
    }
}