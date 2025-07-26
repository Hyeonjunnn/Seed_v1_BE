package com.hyeonjunnn.seed_v1_be.domain.user.service;


import com.hyeonjunnn.seed_v1_be.entity.User;

public interface UserService {
    User getOneUserByUserNo(Long userNo);
}
