package com.ghost.api.system.model;

import lombok.*;

import java.util.List;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/11 02:48
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser {

    private String userName;
    private String password;
    private List<String> authorities;
    private List<String> roles;

}
