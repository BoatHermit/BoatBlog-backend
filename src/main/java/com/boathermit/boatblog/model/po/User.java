package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * 对应表 user
 *
 * @author yinzihang
 * @since 0.1
 */
@Data
public class User {
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
