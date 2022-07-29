package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boathermit.boatblog.dao.ArticleBodyMapper;
import com.boathermit.boatblog.model.po.ArticleBody;
import com.boathermit.boatblog.model.vo.ArticleBodyVo;
import com.boathermit.boatblog.service.ArticleBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:50
 */
@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {

    private final ArticleBodyMapper articleBodyMapper;

    @Autowired
    ArticleBodyServiceImpl(ArticleBodyMapper articleBodyMapper) {
        this.articleBodyMapper = articleBodyMapper;
    }

    @Override
    public ArticleBodyVo findArticleBodyById(Long id) {
        ArticleBody articleBody = articleBodyMapper.selectById(id);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
