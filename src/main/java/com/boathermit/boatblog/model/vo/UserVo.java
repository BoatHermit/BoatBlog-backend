package com.boathermit.boatblog.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:21
 */
@Data
public class UserVo {

    private String nickname;

    private String avatar;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
}
