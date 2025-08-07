package com.hyeonjunnn.seed_v1_be.domain.schedule.repository;

import com.hyeonjunnn.seed_v1_be.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
