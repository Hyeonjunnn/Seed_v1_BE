package com.hyeonjunnn.seed_v1_be.domain.boardCategory.repository;

import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    List<BoardCategory> findBoardCategoriesByIsVisibleEquals(boolean isVisible);
}