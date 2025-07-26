package com.hyeonjunnn.seed_v1_be.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TokenResponseDto {
    private final String accessToken;

    private final String refreshToken;
}
