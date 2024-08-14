package com.ghost.system.service;

import com.ghost.system.entity.SysUser;
import com.ghost.system.entity.SysUserDetails;
import com.ghost.system.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SysSysUserService {

    private final SysUserRepository userRepository;

    public SysSysUserService(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public SysUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public SysUser findById(Long id) {

        SysUser sysUser = userRepository.findById(id).orElse(null);
        if (Objects.nonNull(sysUser)) {
            SysUserDetails sysUserDetails = sysUser.getSysUserDetails();
        }
        return sysUser;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}