package com.boathermit.boatblog.model.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/8/2 8:57
 */
@Data
public class CommentParam {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    private String content;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long toUserId;
}
