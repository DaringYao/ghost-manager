package com.ghost.system.controller;

import com.ghost.model.GhostResult;
import com.ghost.system.entity.SysUser;
import com.ghost.system.service.SysSysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/12 00:38
 */

@RestController
@RequestMapping("sys/user")
public class SysUserController {

    private final SysSysUserService userService;

    public SysUserController(SysSysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("findById")
    public GhostResult<SysUser> findById(Long id) {

        return GhostResult.success(userService.findById(id));

    }

}
