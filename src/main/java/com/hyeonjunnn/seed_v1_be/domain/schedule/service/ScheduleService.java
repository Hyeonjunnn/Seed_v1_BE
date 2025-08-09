package com.hyeonjunnn.seed_v1_be.domain.schedule.service;

import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.Schedule;
import com.hyeonjunnn.seed_v1_be.entity.User;

import java.util.List;

public interface ScheduleService {
    void createSchedule(User user, ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> getSchedules();

    ScheduleResponseDto getSchedule(Long scheduleNo);

    void updateSchedule(User user, Long scheduleNo, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(User user, Long scheduleNo);
}