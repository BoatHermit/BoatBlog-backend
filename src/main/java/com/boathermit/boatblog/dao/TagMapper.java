package com.boathermit.boatblog.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boathermit.boatblog.model.po.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签列表
     *
     * @param articleId 文章id
     * @return 标签列表
     */
    List<Tag> findTagsByArticleId(Long articleId);
}
