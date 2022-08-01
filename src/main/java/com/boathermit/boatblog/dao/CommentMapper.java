package com.boathermit.boatblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boathermit.boatblog.model.po.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Yin Zihang
 * @since 2022/8/1 10:18
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
