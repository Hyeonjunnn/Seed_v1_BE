package com.hyeonjunnn.seed_v1_be.domain.boardCategory.repository;

import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
}