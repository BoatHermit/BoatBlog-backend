package com.boathermit.boatblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yin Zihang
 * @since 2022/7/26 19:44
 */
@AllArgsConstructor
@Getter
public enum Limits {

    //返回的最热标签数量
    HOT_TAG_LIMIT(6),
    //返回的最热文章数量
    HOT_ARTICLE_LIMIT(5),
    //返回的最新文章数量
    NEW_ARTICLE_LIMIT(5);

    private final int limit;
}
