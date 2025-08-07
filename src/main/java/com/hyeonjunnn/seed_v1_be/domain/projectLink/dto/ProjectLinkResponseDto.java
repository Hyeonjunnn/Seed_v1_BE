package com.hyeonjunnn.seed_v1_be.domain.projectLink.dto;

import com.hyeonjunnn.seed_v1_be.entity.ProjectLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLinkResponseDto {
    private Long projectLinkNo;

    private String title;

    private String link;

    private String statusContent;

    public ProjectLinkResponseDto(ProjectLink projectLink) {
        this.projectLinkNo = projectLink.getProjectLinkNo();
        this.title = projectLink.getTitle();
        this.link = projectLink.getLink();
        this.statusContent = projectLink.getStatus().getContent();
    }
}
