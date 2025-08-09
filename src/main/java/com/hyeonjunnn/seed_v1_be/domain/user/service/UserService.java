package com.hyeonjunnn.seed_v1_be.domain.user.service;


import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserResponseDto;
import com.hyeonjunnn.seed_v1_be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponseDto> getUsers(Pageable pageable, String email);

    void updateUser(User user, UserRequestDto userRequestDto);
}
