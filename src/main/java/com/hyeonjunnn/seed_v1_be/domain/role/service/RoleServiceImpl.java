package com.hyeonjunnn.seed_v1_be.domain.role.service;

import com.hyeonjunnn.seed_v1_be.domain.role.dto.RoleRequestDto;
import com.hyeonjunnn.seed_v1_be.domain.role.dto.RoleResponseDto;
import com.hyeonjunnn.seed_v1_be.domain.role.repository.RoleRepository;
import com.hyeonjunnn.seed_v1_be.entity.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void saveRole(RoleRequestDto roleRequestDto) {
        Role role = Role.builder()
                .name(roleRequestDto.getName())
                .build();

        roleRepository.save(role);
    }

    @Override
    public RoleResponseDto getRole(Long roleNo) {
        RoleResponseDto roleResponseDto = roleRepository.findById(roleNo)
                .map(RoleResponseDto::new).orElseThrow(() -> new RuntimeException("존재하지 않는 역할입니다"));

        return roleResponseDto;
    }

    @Override
    public List<RoleResponseDto> getRoles() {
        List<RoleResponseDto> roleResponseDtos = roleRepository.findAll()
                .stream().map(RoleResponseDto::new).collect(Collectors.toList());

        return roleResponseDtos;
    }

    @Transactional
    @Override
    public void updateRole(Long roleNo, RoleRequestDto roleRequestDto) {
        Role role = roleRepository.findById(roleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 역할입니다"));

        role.setName(roleRequestDto.getName());
    }

    @Transactional
    @Override
    public void deleteRole(Long roleNo) {
        Role role = roleRepository.findById(roleNo)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 역할입니다"));

        roleRepository.deleteById(roleNo);
    }
}
