package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.boathermit.boatblog.service.ArticleService;

/**
 *
 * @author yinzihang
 * @since 0.1
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
}
