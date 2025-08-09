package com.hyeonjunnn.seed_v1_be.domain.techCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryResponseDto;

import java.util.List;

public interface TechCategoryService {
    void saveTechCategory(TechCategoryRequestDto techCategoryRequestDto);

    List<TechCategoryResponseDto> getTechCategories();

    void updateTechCategory(Long techCategoryNo, TechCategoryRequestDto techCategoryRequestDto);

    void deleteTechCategory(Long techCategoryNo);
}
