package com.boathermit.boatblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boathermit.boatblog.dao.CommentMapper;
import com.boathermit.boatblog.model.po.Comment;
import com.boathermit.boatblog.model.vo.CommentVo;
import com.boathermit.boatblog.model.vo.UserVo;
import com.boathermit.boatblog.service.CommentService;
import com.boathermit.boatblog.service.UserService;
import com.boathermit.boatblog.utils.Result;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:17
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final UserService userService;

    CommentServiceImpl(CommentMapper commentMapper, UserService userService){
        this.commentMapper = commentMapper;
        this.userService = userService;
    }

    @Override
    public Result getCommentsByArticleId(Long articleId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getLevel, 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(comments);
        return Result.success(commentVos);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            commentVos.add(copy(comment));
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);

        UserVo userVo = userService.findUserVoById(comment.getAuthorId());
        commentVo.setAuthor(userVo);
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        List<CommentVo> commentVoList = findCommentByParentId(comment.getId());
        commentVo.setChildren(commentVoList);
        if (comment.getLevel() > 1) {
            UserVo toUserVo = userService.findUserVoById(comment.getToUid());
            commentVo.setToUser(toUserVo);
        }

        return commentVo;
    }

    private  List<CommentVo> findCommentByParentId(Long parentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,parentId);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }
}