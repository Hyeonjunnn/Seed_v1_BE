package com.hyeonjunnn.seed_v1_be.domain.schedule.service;

import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    void createSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> getSchedules();

    ScheduleResponseDto getSchedule(Long scheduleNo);

    void updateSchedule(Long scheduleNo, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long scheduleNo);
}