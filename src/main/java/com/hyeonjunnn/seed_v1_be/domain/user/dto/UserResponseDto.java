package com.hyeonjunnn.seed_v1_be.domain.user.dto;

import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String userEmail;

    private String userName;

    private Instant createdAt;

    private Instant updatedAt;

    public UserResponseDto(User user) {
        this.userEmail = user.getEmail();
        this.userName = user.getName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
