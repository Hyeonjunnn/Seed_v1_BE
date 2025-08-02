package com.hyeonjunnn.seed_v1_be.domain.role.service;

import com.hyeonjunnn.seed_v1_be.domain.role.dto.RoleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.role.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {
    void saveRole(RoleRequestDto roleRequestDto);

    RoleResponseDto getRole(Long roleNo);

    List<RoleResponseDto> getRoles();

    void updateRole(Long roleNo, RoleRequestDto roleRequestDto);

    void deleteRole(Long roleNo);
}
