package com.hyeonjunnn.seed_v1_be.domain.status.dto;

import com.hyeonjunnn.seed_v1_be.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDto {
    private String statusCode;

    private String content;

    public StatusResponseDto(Status status) {
        this.statusCode = status.getStatusCode();
        this.content = status.getContent();
    }
}
