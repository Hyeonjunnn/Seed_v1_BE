package com.hyeonjunnn.seed_v1_be.domain.status.service;

import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.status.dto.StatusResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.status.repository.StatusRepository;
import com.hyeonjunnn.seed_v1_be.entity.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public void saveStatus(StatusRequestDto statusRequestDto) {
        Status status = Status.builder()
                .statusCode(statusRequestDto.getStatusCode())
                .content(statusRequestDto.getContent())
                .build();

        statusRepository.save(status);
    }

    @Override
    public List<StatusResponseDto> getStatuses() {
        List<StatusResponseDto> statusResponseDtos
                = statusRepository.findAll()
                .stream().map(StatusResponseDto::new)
                .collect(Collectors.toList());

        return statusResponseDtos;
    }

    @Override
    public List<StatusResponseDto> getStatusesByPrefix(String prefix) {
        List<StatusResponseDto> statusResponseDtos
                = statusRepository.findByStatusCodeStartingWith(prefix)
                .stream().map(StatusResponseDto::new)
                .collect(Collectors.toList());

        return statusResponseDtos;
    }

    @Override
    @Transactional
    public void updateStatus(StatusRequestDto statusRequestDto) {
        Status status = statusRepository.findById(statusRequestDto.getStatusCode())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상태입니다."));

        status.setContent(statusRequestDto.getContent());
    }

    @Override
    public void deleteStatus(StatusRequestDto statusRequestDto) {
        Status status = statusRepository.findById(statusRequestDto.getStatusCode())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 상태입니다."));

        statusRepository.delete(status);
    }
}