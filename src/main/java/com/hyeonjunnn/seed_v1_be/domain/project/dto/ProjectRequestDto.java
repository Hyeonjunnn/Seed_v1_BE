package com.hyeonjunnn.seed_v1_be.domain.project.dto;

import com.hyeonjunnn.seed_v1_be.domain.projectLink.dto.ProjectLinkRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.projectTech.dto.ProjectTechRequestDto;
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
public class ProjectRequestDto {
    private String name;

    private String type;

    private String consistOf;

    private String job;

    private String feature;

    private String summary;

    private String detail;

    private Instant startedAt;

    private Instant endedAt;

    private String statusCode;

    private List<ProjectLinkRequestDto> projectLinkRequestDtos;

    private List<ProjectTechRequestDto> projectTechRequestDtos;
}