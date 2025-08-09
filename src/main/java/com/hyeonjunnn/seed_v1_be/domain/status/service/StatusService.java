package com.hyeonjunnn.seed_v1_be.domain.status.service;

import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusResponseDto;

import java.util.List;

public interface StatusService {
    void saveStatus(StatusRequestDto statusRequestDto);

    List<StatusResponseDto> getStatuses();

    List<StatusResponseDto> getStatusesByPrefix(String prefix);

    void updateStatus(StatusRequestDto statusRequestDto);

    void deleteStatus(StatusRequestDto statusRequestDto);
}
