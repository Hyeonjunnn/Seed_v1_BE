package com.hyeonjunnn.seed_v1_be.domain.board.service;

import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.board.dto.BoardResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    void saveBoard(User user, BoardRequestDto boardRequestDto);

    BoardResponseDto getBoard(Long boardNo);

    Page<BoardResponseDto> getBoards(Pageable pageable, User user, Long boardCategoryNo, String title);

    void deleteBoard(User user, Long boardNo);

    void updateBoard(User user, Long boardNo, BoardRequestDto boardRequestDto);
}
