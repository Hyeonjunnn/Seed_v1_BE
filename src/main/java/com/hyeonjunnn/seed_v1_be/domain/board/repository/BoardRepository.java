package com.hyeonjunnn.seed_v1_be.domain.board.repository;

import com.hyeonjunnn.seed_v1_be.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    Page<Board> findBoardsByBoardCategory_BoardCategoryNo(Pageable pageable, Long boardCategoryNo);

    Page<Board> findBoardsByBoardCategory_BoardCategoryNoAndTitleContains(Pageable pageable, Long boardCategoryNo, String boardTitle);
}
