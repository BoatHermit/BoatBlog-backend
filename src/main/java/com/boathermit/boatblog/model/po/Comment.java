package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:07
 */
@Data
public class Comment {
    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
