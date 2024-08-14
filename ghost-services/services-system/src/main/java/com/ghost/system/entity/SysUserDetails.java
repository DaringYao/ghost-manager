package com.ghost.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserDetails)实体类
 *
 * @author yaolsa
 * @since 2024-08-13 23:38:40
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(SysUserDetails.SysUserDetailsPK.class)
@Table(name = "sys_user_details")
public class SysUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String bio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SysUserDetailsPK implements Serializable {

        private Integer Id;

        private Integer userId;

    }

}

