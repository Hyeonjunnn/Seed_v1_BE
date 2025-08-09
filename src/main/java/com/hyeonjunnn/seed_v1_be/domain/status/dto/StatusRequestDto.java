package com.hyeonjunnn.seed_v1_be.domain.status.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequestDto {
    private String statusCode;

    private String content;
}
