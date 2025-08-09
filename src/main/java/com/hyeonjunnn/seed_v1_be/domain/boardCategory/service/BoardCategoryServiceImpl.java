package com.hyeonjunnn.seed_v1_be.domain.boardCategory.service;

import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto.BoardCategoryResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.boardCategory.repository.BoardCategoryRepository;
import com.hyeonjunnn.seed_v1_be.domain.permission.repository.PermissionRepository;
import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {
    private final BoardCategoryRepository boardCategoryRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void saveBoardCategory(BoardCategoryRequestDto boardCategoryRequestDto) {

        BoardCategory boardCategory = BoardCategory.builder()
                .name(boardCategoryRequestDto.getName())
                .build();

        boardCategoryRepository.save(boardCategory);
    }

    @Override
    public List<BoardCategoryResponseDto> getBoard_categories(User user, String method) {
        List<BoardCategoryResponseDto> boardCategoryResponseDtos;
        List<String> allowedCategories;

        if (user == null) {
            allowedCategories = permissionRepository
                    .findCategoriesByRoleRoleNoIsNullAndDomainAndMethod("BOARD", method);
        } else {
            allowedCategories = permissionRepository
                    .findCategoriesByRoleRoleNoAndDomainAndMethod(user.getRole().getRoleNo(), "BOARD", method);
        }

        if (user != null && user.getRole().getName().equals("ADMIN")) {
            boardCategoryResponseDtos = boardCategoryRepository.findAll()
                    .stream().map(BoardCategoryResponseDto::new)
                    .collect(Collectors.toList());
        } else {
            boardCategoryResponseDtos = boardCategoryRepository.findBoardCategoriesByNameInAndIsVisibleTrue(allowedCategories)
                    .stream().map(BoardCategoryResponseDto::new)
                    .collect(Collectors.toList());
        }

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