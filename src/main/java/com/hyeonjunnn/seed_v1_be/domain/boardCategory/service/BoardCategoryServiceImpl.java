package com.hyeonjunnn.seed_v1_be.domain.boardCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.repository.BoardCategoryRepository;
import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {
    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    public void saveBoardCategory(BoardCategoryRequestDto boardCategoryRequestDto) {

        BoardCategory boardCategory = BoardCategory.builder()
                .name(boardCategoryRequestDto.getName())
                .build();

        boardCategoryRepository.save(boardCategory);
    }

//    @Override
//    public BoardCategory getBoardCategory(Long boardCategoryNo) {
//        BoardCategory boardCategory = boardCategoryRepository.findById(boardCategoryNo)
//                .map(BoardResponseDto::new).orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
//
//        return null;
//    }

    @Override
    public List<BoardCategoryResponseDto> getBoard_categories() {
        List<BoardCategoryResponseDto> boardCategoryResponseDtos = boardCategoryRepository.findAll()
                .stream().map(BoardCategoryResponseDto::new).collect(Collectors.toList());

        return boardCategoryResponseDtos;
    }

    @Override
    @Transactional
    public void updateBoardCategory(Long boardCategoryNo, BoardCategoryRequestDto boardCategoryRequestDto) {
        BoardCategory boardCategory = boardCategoryRepository.findById(boardCategoryNo)
                        .orElseThrow(() -> new RuntimeException("게시판 카테고리를 찾을 수 없습니다."));

        boardCategory.setName(boardCategoryRequestDto.getName());

        boardCategoryRepository.save(boardCategory);
    }

    @Override
    public void deleteBoardCategory(Long boardCategoryNo) {
        boardCategoryRepository.deleteById(boardCategoryNo);
    }
}