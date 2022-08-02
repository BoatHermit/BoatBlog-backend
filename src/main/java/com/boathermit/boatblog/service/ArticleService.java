package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.param.ArticleParam;
import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.model.vo.ArchivesVo;
import com.boathermit.boatblog.model.vo.ArticleVo;
import com.boathermit.boatblog.utils.Result;

import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
public interface ArticleService {

    /**
     * 分页查询文章列表
     *
     * @param param 分页信息，包括当前页数和每页显示的数量
     * @return 查询结果
     */
    List<ArticleVo> listArticle(PageParam param);

    /**
     * 返回{@code limit}个热门文章
     *
     * @param limit 返回的文章个数
     * @return 返回热门文章列表
     */
    List<ArticleVo> hot(int limit);

    /**
     * 返回{@code limit}个最新文章
     *
     * @param limit 返回的文章个数
     * @return 返回最新文章列表
     */
    List<ArticleVo> newArticles(int limit);

    /**
     * 文章归档
     *
     * @return 返回最新文章列表
     */
    List<ArchivesVo> listArchives();

    /**
     * 根据文章id返回文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    ArticleVo findArticleById(Long id);

    /**
     * 创建文章
     * @param articleParam 文章信息
     * @return 成功信息，含文章id
     */
    Result publish(ArticleParam articleParam);
}
