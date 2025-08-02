package com.hyeonjunnn.seed_v1_be.domain.board_category.dto;

import com.hyeonjunnn.seed_v1_be.entity.Board_category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board_categoryResponseDto {
    private Long boardCategoryNo;

    private String name;

    public Board_categoryResponseDto(Board_category boardCategory) {
        this.boardCategoryNo = boardCategory.getBoardCategoryNo();
        this.name = boardCategory.getName();
    }
}
