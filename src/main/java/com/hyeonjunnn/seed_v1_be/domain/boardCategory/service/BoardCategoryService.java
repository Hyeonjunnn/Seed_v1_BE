package com.hyeonjunnn.seed_v1_be.domain.boardCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.User;

import java.util.List;

public interface BoardCategoryService {

    void saveBoardCategory(BoardCategoryRequestDto boardCategoryRequestDto);

    List<BoardCategoryResponseDto> getBoard_categories(User user, String method);

    void updateBoardCategory(Long boardCategoryNo, BoardCategoryRequestDto boardCategoryRequestDto);

    void deleteBoardCategory(Long boardCategoryNo);
}
