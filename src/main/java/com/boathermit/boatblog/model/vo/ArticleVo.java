package com.boathermit.boatblog.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;

    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;
}
