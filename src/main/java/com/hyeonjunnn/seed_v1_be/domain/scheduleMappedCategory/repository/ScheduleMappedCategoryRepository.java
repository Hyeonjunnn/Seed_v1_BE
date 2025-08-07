package com.hyeonjunnn.seed_v1_be.domain.scheduleMappedCategory.repository;

import com.hyeonjunnn.seed_v1_be.entity.ScheduleMappedCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleMappedCategoryRepository
        extends JpaRepository<ScheduleMappedCategory, Long> {
}
