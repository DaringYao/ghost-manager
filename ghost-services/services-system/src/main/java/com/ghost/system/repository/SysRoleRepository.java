package com.ghost.system.repository;

import com.ghost.system.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    SysRole findByName(String name);
}