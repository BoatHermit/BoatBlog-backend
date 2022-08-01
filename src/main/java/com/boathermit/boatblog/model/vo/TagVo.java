package com.boathermit.boatblog.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author yinzihang
 * @since 0.1
 */
@Data
public class TagVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String tagName;
}
