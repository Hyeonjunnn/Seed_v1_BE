package com.hyeonjunnn.seed_v1_be.domain.user.service;

import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.user.dto.UserResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.user.repository.UserRepository;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<UserResponseDto> getUsers(Pageable pageable, String email) {
        Page<UserResponseDto> userResponseDtos
                = userRepository.findUsersByEmailContains(pageable, email)
                .map(UserResponseDto::new);

        return userResponseDtos;
    }

    @Override
    @Transactional
    public void updateUser(User user, UserRequestDto userRequestDto) {
        if (!user.getUserNo().equals(userRequestDto.getUserNo())) {
            throw new RuntimeException("본인 계정이 아닙니다.");
        }

        user.setName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getUserEmail());
        user.setDeleted(userRequestDto.isDeleted());
        user.setDeletedAt(Instant.now());
        user.setDeletedContent(userRequestDto.getDeletedContent());
    }
}