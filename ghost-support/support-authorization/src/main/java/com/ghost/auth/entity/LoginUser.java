package com.ghost.auth.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/3 01:14
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private String username;
    private String password;
    private List<UserAuthority> authorities;

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAuthority implements GrantedAuthority{
        private String authority;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
