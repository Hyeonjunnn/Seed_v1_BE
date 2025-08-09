package com.hyeonjunnn.seed_v1_be.domain.projectTech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechRequestDto {
    private Long projectNo;

    private Long techNo;
}
