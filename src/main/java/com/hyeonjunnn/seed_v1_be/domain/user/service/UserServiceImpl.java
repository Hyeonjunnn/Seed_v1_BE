package com.hyeonjunnn.seed_v1_be.domain.user.service;

import com.hyeonjunnn.seed_v1_be.domain.user.repository.UserRepository;
import com.hyeonjunnn.seed_v1_be.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getOneUserByUserNo(Long userNo) {

        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        return user;
    }


}
