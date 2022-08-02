package com.boathermit.boatblog.controller;

import com.boathermit.boatblog.model.param.CommentParam;
import com.boathermit.boatblog.service.CommentService;
import com.boathermit.boatblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/change")
    public Result addComment(@RequestBody CommentParam commentParam) {
        return commentService.addComment(commentParam);
    }
}
