package com.ghost.system.repository;

import com.ghost.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);

    SysUser findByEmail(String email);
}