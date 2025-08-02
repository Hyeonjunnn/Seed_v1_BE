package com.hyeonjunnn.seed_v1_be.domain.boardCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryRequestDto {
    private Long boardCategoryNo;

    private String name;
}
