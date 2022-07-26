package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.vo.TagVo;

import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
public interface TagService {

    /**
     * 根据文章ID返回对应的文章标签
     *
     * @param articleId 文章ID
     * @return 标签列表
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 返回{@code limit}个热门标签
     * @param limit 返回的标签个数
     * @return 返回热门标签列表
     */
    List<TagVo> hot(int limit);
}
