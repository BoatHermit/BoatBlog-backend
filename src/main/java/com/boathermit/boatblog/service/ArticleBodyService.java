package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.vo.ArticleBodyVo;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:35
 */
public interface ArticleBodyService {

    /**
     * 通过文章内容id获取文章内容
     * @param id id
     * @return 文章内容
     */
    ArticleBodyVo findArticleBodyById(Long id);
}
