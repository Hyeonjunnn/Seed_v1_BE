package com.hyeonjunnn.seed_v1_be.domain.schedule.dto;

import com.hyeonjunnn.seed_v1_be.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleNo;

    private String title;

    private String content;

    private Instant startedAt;

    private Instant endedAt;

    private Instant createdAt;

    private Instant updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleNo = schedule.getScheduleNo();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.startedAt = schedule.getStartedAt();
        this.endedAt = schedule.getEndedAt();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}