package com.hyeonjunnn.seed_v1_be.domain.role.dto;

import com.hyeonjunnn.seed_v1_be.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {
    private Long roleNo;

    private String name;

    public RoleResponseDto(Role role) {
        this.roleNo = role.getRoleNo();
        this.name = role.getName();
    }
}
