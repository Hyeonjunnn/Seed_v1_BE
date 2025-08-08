package com.hyeonjunnn.seed_v1_be.domain.schedule.service;

import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.dto.ScheduleResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.schedule.repository.ScheduleRepository;
import com.hyeonjunnn.seed_v1_be.domain.scheduleCategory.repository.ScheduleCategoryRepository;
import com.hyeonjunnn.seed_v1_be.domain.scheduleMappedCategory.repository.ScheduleMappedCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.ProjectTech;
import com.hyeonjunnn.seed_v1_be.entity.Schedule;
import com.hyeonjunnn.seed_v1_be.entity.ScheduleCategory;
import com.hyeonjunnn.seed_v1_be.entity.ScheduleMappedCategory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMappedCategoryRepository scheduleMappedCategoryRepository;
    private final ScheduleCategoryRepository scheduleCategoryRepository;

    @Override
    public void createSchedule(ScheduleRequestDto scheduleRequestDto) {

    }

    @Override
    public List<ScheduleResponseDto> getSchedules() {
        return null;
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
    public void updateSchedule(Long scheduleNo, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

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
    public void deleteSchedule(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        scheduleRepository.delete(schedule);
    }
}