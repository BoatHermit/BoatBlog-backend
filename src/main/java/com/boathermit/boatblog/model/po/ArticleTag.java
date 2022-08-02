package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/8/2 15:38
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}
