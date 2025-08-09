package com.hyeonjunnn.seed_v1_be.domain.permission.repository;

import com.hyeonjunnn.seed_v1_be.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT p.category " +
            "FROM Permission p " +
            "WHERE p.role.roleNo = :roleNo " +
            "AND p.domain = :domain " +
            "AND p.method = :method")
    List<String> findCategoriesByRoleRoleNoAndDomainAndMethod(Long roleNo, String domain, String method);

    @Query("SELECT p.category " +
            "FROM Permission p " +
            "WHERE p.role.roleNo IS NULL " +
            "AND p.domain = :domain " +
            "AND p.method = :method")
    List<String> findCategoriesByRoleRoleNoIsNullAndDomainAndMethod(String domain, String method);

}