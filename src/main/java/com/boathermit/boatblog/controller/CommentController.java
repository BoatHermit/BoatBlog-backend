package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.service.CommentService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:12
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId) {
        return commentService.getCommentsByArticleId(articleId);
    }


}
