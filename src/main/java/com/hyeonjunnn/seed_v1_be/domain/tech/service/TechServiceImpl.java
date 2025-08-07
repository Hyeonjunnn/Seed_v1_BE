package com.hyeonjunnn.seed_v1_be.domain.tech.service;

import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.tech.repository.TechRepository;
import com.hyeonjunnn.seed_v1_be.domain.techCategory.repository.TechCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.Tech;
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
public class TechServiceImpl implements TechService {
    private final TechRepository techRepository;
    private final TechCategoryRepository techCategoryRepository;

    @Override
    public void createTech(TechRequestDto techRequestDto) {
        TechCategory techCategory =
                techCategoryRepository.findById(techRequestDto.getTechCategoryNo())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 기술 카테고리입니다."));

        Tech tech = Tech.builder()
                .name(techRequestDto.getTechName())
                .techCategory(techCategory)
                .build();

        techRepository.save(tech);
    }

    @Override
    public List<TechResponseDto> getTechs() {
        List<TechResponseDto> techResponseDtos
                = techRepository.findAll()
                .stream().map(TechResponseDto::new)
                .collect(Collectors.toList());

        return techResponseDtos;
    }

    @Override
    @Transactional
    public void updateTech(Long techNo, TechRequestDto techRequestDto) {
        Tech tech = techRepository.findById(techNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기술입니다."));

        tech.setName(techRequestDto.getTechName());
        tech.setTechCategory(TechCategory.builder()
                .techCategoryNo(techRequestDto.getTechCategoryNo())
                .build());
    }

    @Override
    public void deleteTech(Long techNo) {
        techRepository.findById(techNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 기술입니다."));

        techRepository.deleteById(techNo);
    }
}