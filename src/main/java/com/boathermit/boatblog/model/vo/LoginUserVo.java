package com.boathermit.boatblog.model.vo;

import com.boathermit.boatblog.enums.ROLE;
import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/7/27 15:49
 */
@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;

    private ROLE role;
}
