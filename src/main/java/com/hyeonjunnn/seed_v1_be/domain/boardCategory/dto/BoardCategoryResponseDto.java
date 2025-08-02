package com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto;

import com.hyeonjunnn.seed_v1_be.entity.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryResponseDto {
    private Long boardCategoryNo;

    private String name;

    public BoardCategoryResponseDto(BoardCategory boardCategory) {
        this.boardCategoryNo = boardCategory.getBoardCategoryNo();
        this.name = boardCategory.getName();
    }
}
