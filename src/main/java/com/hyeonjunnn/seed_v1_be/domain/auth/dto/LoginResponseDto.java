package com.hyeonjunnn.seed_v1_be.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private final String userName;

    private final String accessToken;

    private final String refreshToken;
}
