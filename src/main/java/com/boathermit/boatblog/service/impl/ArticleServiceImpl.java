package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.model.po.Article;
import com.boathermit.boatblog.model.vo.ArchivesVo;
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

    /**
     * 排序属性表
     */
    private enum KEYS {
        // 查看人数
        ViewCounts,
        //创建日期
        CreateDate
    }

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

    @Override
    public List<ArticleVo> hot(int limit) {
        return getArticles(limit, KEYS.ViewCounts);
    }

    @Override
    public List<ArticleVo> newArticles(int limit) {
        return getArticles(limit, KEYS.CreateDate);
    }

    @Override
    public List<ArchivesVo> listArchives() {
        return articleMapper.listArchives();
    }

    private List<ArticleVo> getArticles(int limit, KEYS key) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (key == KEYS.ViewCounts) {
            queryWrapper.orderByDesc(Article::getViewCounts);
        } else if (key == KEYS.CreateDate) {
            queryWrapper.orderByDesc(Article::getCreateDate);
        }
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit "+limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);

        return copyList(articles,false,false);
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
