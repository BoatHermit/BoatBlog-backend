package com.boathermit.boatblog.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:07
 */
@Data
public class CategoryVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String avatar;

    private String categoryName;
}
