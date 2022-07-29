package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:01
 */
@Data
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}
