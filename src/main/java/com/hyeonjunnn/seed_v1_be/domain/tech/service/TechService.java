package com.hyeonjunnn.seed_v1_be.domain.tech.service;

import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.tech.dto.TechResponseDto;

import java.util.List;

public interface TechService {
    void createTech(TechRequestDto techRequestDto);

    List<TechResponseDto> getTechs();

    void updateTech(Long techNo, TechRequestDto techRequestDto);

    void deleteTech(Long techNo);
}
