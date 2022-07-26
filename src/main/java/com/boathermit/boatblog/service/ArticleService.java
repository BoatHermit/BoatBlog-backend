package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.model.vo.ArticleVo;

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
}