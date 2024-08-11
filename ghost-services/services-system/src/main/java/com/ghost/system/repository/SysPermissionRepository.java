package com.ghost.system.repository;

import com.ghost.system.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

   SysPermission findByCode(String code);
}