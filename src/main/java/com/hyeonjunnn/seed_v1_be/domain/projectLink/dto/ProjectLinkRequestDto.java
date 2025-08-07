package com.hyeonjunnn.seed_v1_be.domain.projectLink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLinkRequestDto {
    private Long projectLinkNo;

    private String title;

    private String link;

    private String statusCode;
}
