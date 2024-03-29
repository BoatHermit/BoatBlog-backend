package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boathermit.boatblog.dao.ArticleBodyMapper;
import com.boathermit.boatblog.dao.ArticleTagMapper;
import com.boathermit.boatblog.model.param.ArticleParam;
import com.boathermit.boatblog.model.param.PageParam;
import com.boathermit.boatblog.model.po.Article;
import com.boathermit.boatblog.model.po.ArticleBody;
import com.boathermit.boatblog.model.po.ArticleTag;
import com.boathermit.boatblog.model.po.User;
import com.boathermit.boatblog.model.vo.*;
import com.boathermit.boatblog.service.*;
import com.boathermit.boatblog.dao.ArticleMapper;

import com.boathermit.boatblog.utils.Result;
import com.boathermit.boatblog.utils.UserThreadLocal;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleTagMapper articleTagMapper;
    private final ArticleBodyMapper articleBodyMapper;

    private final TagService tagService;
    private final UserService userService;
    private final ArticleBodyService articleBodyService;
    private final CategoryService categoryService;
    private final ThreadService threadService;

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
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleTagMapper articleTagMapper, ArticleBodyMapper articleBodyMapper, TagService tagService, UserService userService,
                              ArticleBodyService articleBodyService, CategoryService categoryService, ThreadService threadService) {
        this.articleMapper = articleMapper;
        this.articleTagMapper = articleTagMapper;
        this.articleBodyMapper = articleBodyMapper;
        this.tagService = tagService;
        this.userService = userService;
        this.articleBodyService = articleBodyService;
        this.categoryService = categoryService;
        this.threadService = threadService;
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

    @Override
    public ArticleVo findArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        threadService.updateViewCount(articleMapper, article);
        return copy(articleMapper.selectById(id), true, true, true, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result publish(ArticleParam articleParam) {
        User user = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(user.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        article.setCreateDate(System.currentTimeMillis());
        article.setCommentCounts(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.ARTICLE_COMMON);
        article.setBodyId(-1L);
        this.articleMapper.insert(article);

        //tags
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tag.getId());
                articleTagMapper.insert(articleTag);
            }
        }
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        return Result.success(articleVo);
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
             articleVoList.add(copy(record, isTag, isAuthor, false, false));
        }

        return articleVoList;
    }

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor,
                           boolean isBody, boolean isCategory) {
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
        if (isBody) {
            ArticleBodyVo articleBodyVo = articleBodyService.findArticleBodyById(article.getBodyId());
            articleVo.setBody(articleBodyVo);
        }

        if (isCategory) {
            CategoryVo categoryVo = categoryService.findCategoryById(article.getCategoryId());
            articleVo.setCategory(categoryVo);
        }

        return articleVo;
    }
}
