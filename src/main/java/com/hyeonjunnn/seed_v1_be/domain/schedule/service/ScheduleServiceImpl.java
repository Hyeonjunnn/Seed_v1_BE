package com.hyeonjunnn.seed_v1_be.domain.schedule.service;

import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.repository.ScheduleRepository;
import com.hyeonjunnn.seed_v1_be.domain.scheduleCategory.repository.ScheduleCategoryRepository;
import com.hyeonjunnn.seed_v1_be.domain.scheduleMappedCategory.repository.ScheduleMappedCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.Schedule;
import com.hyeonjunnn.seed_v1_be.entity.ScheduleCategory;
import com.hyeonjunnn.seed_v1_be.entity.ScheduleMappedCategory;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMappedCategoryRepository scheduleMappedCategoryRepository;
    private final ScheduleCategoryRepository scheduleCategoryRepository;

    @Override
    public void createSchedule(User user, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = Schedule.builder()
                .title(scheduleRequestDto.getTitle())
                .content(scheduleRequestDto.getContent())
                .startedAt(scheduleRequestDto.getStartedAt())
                .endedAt(scheduleRequestDto.getEndedAt())
                .user(user)
                .build();

        scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleResponseDto> getSchedules() {
        List<ScheduleResponseDto> scheduleResponseDtos
                = scheduleRepository.findAll()
                .stream().map(ScheduleResponseDto::new)
                .collect(Collectors.toList());

        return scheduleResponseDtos;
    }

    @Override
    public ScheduleResponseDto getSchedule(Long scheduleNo) {
        ScheduleResponseDto scheduleResponseDto
                = scheduleRepository.findById(scheduleNo)
                .map(ScheduleResponseDto::new)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        return scheduleResponseDto;
    }

    @Override
    @Transactional
    public void updateSchedule(User user, Long scheduleNo, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        if (!user.getUserNo().equals(schedule.getUser().getUserNo())) {
            throw new RuntimeException("작성자가 아닙니다.");
        }

        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContent(scheduleRequestDto.getContent());
        schedule.setStartedAt(scheduleRequestDto.getStartedAt());
        schedule.setEndedAt(scheduleRequestDto.getEndedAt());

        if (scheduleMappedCategoryRepository.deleteAllByScheduleScheduleNo(scheduleNo)){
            scheduleRequestDto.getScheduleCategoriesNo()
                    .forEach(scheduleCategoryNo -> {
                        ScheduleCategory scheduleCategory
                                = scheduleCategoryRepository.findById(scheduleCategoryNo)
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정 카테고리입니다."));

                        ScheduleMappedCategory scheduleMappedCategory
                                = ScheduleMappedCategory.builder()
                                .id(new ScheduleMappedCategory.ScheduleMappedCategoryId(
                                        scheduleNo, scheduleCategoryNo
                                ))
                                .schedule(schedule)
                                .scheduleCategory(scheduleCategory)
                                .build();

                        scheduleMappedCategoryRepository.save(scheduleMappedCategory);
                    });
        }
    }

    @Override
    @Transactional
    public void deleteSchedule(User user, Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        if (!user.getUserNo().equals(schedule.getUser().getUserNo())) {
            throw new RuntimeException("작성자가 아닙니다.");
        }

        scheduleRepository.delete(schedule);
    }
}