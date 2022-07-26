package com.boathermit.boatblog.model.po;

import lombok.Data;

/**
 * 对应表 article
 *
 * @author yinzihang
 * @since 0.1
 */
@Data
public class Article {
    public static final int ARTICLE_TOP = 1;

    public static final int ARTICLE_COMMON = 0;

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 内容id
     */
    private Long bodyId;

    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private int weight = ARTICLE_COMMON;

    /**
     * 创建时间
     */
    private Long createDate;
}
