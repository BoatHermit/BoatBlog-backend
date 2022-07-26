package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.model.po.Article;
import com.boathermit.boatblog.model.vo.ArticleVo;
import com.boathermit.boatblog.service.TagService;
import com.boathermit.boatblog.service.UserService;
import com.boathermit.boatblog.dao.ArticleMapper;
import com.boathermit.boatblog.service.ArticleService;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final TagService tagService;

    private final UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, TagService tagService, UserService userService) {
        this.articleMapper = articleMapper;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public List<ArticleVo> listArticle(PageParam param) {

        Page<Article> page = new Page<>(param.getPage(), param.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight).orderByDesc(Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        return copyList(records, true, true);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record: records) {
             articleVoList.add(copy(record, isTag, isAuthor));
        }

        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(userService.findUserById(authorId).getNickname());
        }

        return articleVo;
    }
}