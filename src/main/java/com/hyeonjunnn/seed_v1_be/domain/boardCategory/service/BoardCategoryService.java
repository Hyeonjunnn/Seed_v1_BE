package com.hyeonjunnn.seed_v1_be.domain.boardCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryResponseDto;

import java.util.List;

public interface BoardCategoryService {

    void saveBoardCategory(BoardCategoryRequestDto boardCategoryRequestDto);

//    BoardCategory getBoardCategory(Long boardCategoryNo);

    List<BoardCategoryResponseDto> getBoard_categories();

    void updateBoardCategory(Long boardCategoryNo, BoardCategoryRequestDto boardCategoryRequestDto);

    void deleteBoardCategory(Long boardCategoryNo);
}
