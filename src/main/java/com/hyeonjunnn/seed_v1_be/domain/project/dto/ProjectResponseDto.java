package com.hyeonjunnn.seed_v1_be.domain.project.dto;

import com.hyeonjunnn.seed_v1_be.domain.projectLink.dto.ProjectLinkResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {
    private Long projectNo;

    private String name;

    private String type;

    private String consistOf;

    private String job;

    private String feature;

    private String summary;

    private String detail;

    private Instant startedAt;

    private Instant endedAt;

    private boolean isVisible;

    private String statusContent;

    private List<ProjectLinkResponseDto> projectLinkResponseDtos;

    private List<ProjectTechResponseDto> projectTechResponseDtos;

    public ProjectResponseDto(Project project) {
        this.projectNo = project.getProjectNo();
        this.name = project.getName();
        this.type = project.getType();
        this.consistOf = project.getConsistOf();
        this.job = project.getJob();
        this.feature = project.getFeature();
        this.summary = project.getSummary();
        this.detail = project.getDetail();
        this.startedAt = project.getStartedAt();
        this.endedAt = project.getEndedAt();
        this.isVisible = project.getIsVisible();
        this.statusContent = project.getStatus().getContent();
    }
}