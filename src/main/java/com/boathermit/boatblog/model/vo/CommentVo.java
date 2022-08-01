package com.boathermit.boatblog.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:20
 */
@Data
public class CommentVo {

    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> children;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
