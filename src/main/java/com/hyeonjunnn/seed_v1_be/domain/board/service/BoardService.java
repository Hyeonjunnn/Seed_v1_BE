package com.hyeonjunnn.seed_v1_be.domain.board.service;

import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    void saveBoard(BoardRequestDto boardRequestDto);

    BoardResponseDto getBoard(Long boardNo);

    Page<BoardResponseDto> getBoards(Pageable pageable, Long boardCategoryNo, String title);

    void deleteBoard(Long boardNo);

    void updateBoard(Long boardNo, BoardRequestDto boardRequestDto);
}
