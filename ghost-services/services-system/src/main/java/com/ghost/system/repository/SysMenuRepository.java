package com.ghost.system.repository;

import com.ghost.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    List<SysMenu> findByParentIsNullOrderByOrderAsc();
}