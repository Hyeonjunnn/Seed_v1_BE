package com.hyeonjunnn.seed_v1_be.global.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
}
