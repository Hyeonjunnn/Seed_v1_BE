package com.hyeonjunnn.seed_v1_be.domain.techCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.dto.TechCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.repository.TechCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.TechCategory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TechCategoryServiceImpl implements TechCategoryService {
    private final TechCategoryRepository techCategoryRepository;

    @Override
    public void createTechCategory(TechCategoryRequestDto techCategoryRequestDto) {

    }

    @Override
    public List<TechCategoryResponseDto> getTechCategories() {
        List<TechCategoryResponseDto> techCategoryResponseDtos
                = techCategoryRepository.findAll()
                .stream().map(TechCategoryResponseDto::new)
                .collect(Collectors.toList());

        return techCategoryResponseDtos;
    }

    @Override
    @Transactional
    public void updateTechCategory(Long techCategoryNo, TechCategoryRequestDto techCategoryRequestDto) {
        TechCategory techCategory
                = techCategoryRepository.findById(techCategoryNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기술 카테고리입니다."));

        techCategory.setName(techCategoryRequestDto.getTechCategoryName());
    }

    @Override
    public void deleteTechCategory(Long techCategoryNo) {
        techCategoryRepository.findById(techCategoryNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기술 카테고리입니다."));

        techCategoryRepository.deleteById(techCategoryNo);
    }
}
