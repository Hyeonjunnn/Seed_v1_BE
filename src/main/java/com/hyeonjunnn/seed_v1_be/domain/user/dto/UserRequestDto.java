package com.hyeonjunnn.seed_v1_be.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long userNo;

    private String userEmail;

    private String userName;

    private boolean isDeleted;

    private String deletedContent;
}
