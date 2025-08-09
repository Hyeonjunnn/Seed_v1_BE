package com.hyeonjunnn.seed_v1_be.domain.projectTech.dto;

import com.hyeonjunnn.seed_v1_be.entity.ProjectTech;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechResponseDto {
    private String techName;

    public ProjectTechResponseDto(ProjectTech projectTech) {
        this.techName = projectTech.getTech().getName();
    }
}
