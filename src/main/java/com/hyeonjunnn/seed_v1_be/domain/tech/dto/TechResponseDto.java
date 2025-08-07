package com.hyeonjunnn.seed_v1_be.domain.tech.dto;

import com.hyeonjunnn.seed_v1_be.entity.Tech;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechResponseDto {
    private String techName;

    private String techCategoryName;

    public TechResponseDto(Tech tech) {
        this.techName = tech.getName();
        this.techCategoryName = tech.getTechCategory().getName();
    }
}
