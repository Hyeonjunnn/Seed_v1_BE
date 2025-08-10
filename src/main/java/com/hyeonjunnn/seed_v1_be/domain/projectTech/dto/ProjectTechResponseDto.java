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
    private Long techNo;
    private String techName;
    private String techCategoryName;

    public ProjectTechResponseDto(ProjectTech projectTech) {
        this.techNo = projectTech.getTech().getTechNo();
        this.techName = projectTech.getTech().getName();
        this.techCategoryName = projectTech.getTech().getTechCategory().getName();
    }
}