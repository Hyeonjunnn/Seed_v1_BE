package com.hyeonjunnn.seed_v1_be.domain.role.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {
    private Long roleNo;

    private String name;
}
