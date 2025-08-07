package com.hyeonjunnn.seed_v1_be.domain.techCategory.dto;

import com.hyeonjunnn.seed_v1_be.entity.TechCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechCategoryResponseDto {
    private String techCategoryName;

    public TechCategoryResponseDto(TechCategory techCategory) {
        this.techCategoryName = techCategory.getName();
    }
}
