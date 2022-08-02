package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.param.CommentParam;
import com.boathermit.boatblog.utils.Result;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:15
 */
public interface CommentService {

    /**
     * 根据文章id获取对应的评论
     * @param articleId 文章id
     * @return 评论列表
     */
    Result getCommentsByArticleId(Long articleId);

    /**
     * 添加评论
     * @param commentParam 评论信息
     * @return 是否成功
     */
    Result addComment(CommentParam commentParam);
}
