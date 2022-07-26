package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.enums.Limits;
import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.boathermit.boatblog.service.ArticleService;

/**
 *
 * @author yinzihang
 * @since 0.1
 * @see com.boathermit.boatblog.service.impl.ArticleServiceImpl
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 首页 文章列表
     *
     * @param param 分页信息，包括当前页数和每页显示的数量
     * @return 查询结果
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParam param) {
        return Result.success(articleService.listArticle(param));
    }

    /**
     * 首页 最热文章列表
     *
     * @return 最热文章列表
     */
    @PostMapping("/hot")
    public Result listHotArticles() {
        return Result.success(articleService.hot(Limits.HOT_ARTICLE_LIMIT.getLimit()));
    }

    /**
     * 首页 最新文章
     *
     * @return 最热文章列表
     */
    @PostMapping("/new")
    public Result newArticles(){
        return Result.success(articleService.newArticles(Limits.NEW_ARTICLE_LIMIT.getLimit()));
    }

    /**
     * 首页 文章归档
     * @return 归档列表
     */
    @PostMapping("/listArchives")
    public Result listArchives(){
        return Result.success(articleService.listArchives());
    }
}
