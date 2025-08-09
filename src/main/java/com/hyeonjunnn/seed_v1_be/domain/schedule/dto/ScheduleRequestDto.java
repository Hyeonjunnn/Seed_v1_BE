package com.hyeonjunnn.seed_v1_be.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;

    private String content;

    private Instant startedAt;

    private Instant endedAt;

    private List<Long> scheduleCategoriesNo;
}
