package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.annotation.Log;
import com.boathermit.boatblog.enums.Limits;
import com.boathermit.boatblog.model.param.ArticleParam;
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
    @Log(module = "文章", operation = "获取文章列表")
    @PostMapping
    public Result listArticle(@RequestBody PageParam param) {
        return Result.success(articleService.listArticle(param));
    }

    /**
     * 首页 最热文章列表
     *
     * @return 最热文章列表
     */
    @Log(module = "文章", operation = "获取最热文章列表")
    @PostMapping("/hot")
    public Result listHotArticles() {
        return Result.success(articleService.hot(Limits.HOT_ARTICLE_LIMIT.getLimit()));
    }

    /**
     * 首页 最新文章
     *
     * @return 最热文章列表
     */
    @Log(module = "文章", operation = "获取最新文章列表")
    @PostMapping("/new")
    public Result newArticles(){
        return Result.success(articleService.newArticles(Limits.NEW_ARTICLE_LIMIT.getLimit()));
    }

    /**
     * 首页 文章归档
     * @return 归档列表
     */
    @Log(module = "文章", operation = "获取文章归档列表")
    @PostMapping("/listArchives")
    public Result listArchives(){
        return Result.success(articleService.listArchives());
    }

    /**
     * 文章详情
     * @param id 文章id
     * @return 文章详情
     */
    @Log(module = "文章", operation = "获取最热文章详情")
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id) {
        return Result.success(articleService.findArticleById(id));
    }

    /**
     * 发布文章
     *
     * @param articleParam 文章信息
     * @return 成功信息
     */
    @Log(module = "文章", operation = "发布文章")
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }
}
