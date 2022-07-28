package com.boathermit.boatblog.dao;

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

    /**
     * 根据标签热度（标签下文章数量）返回前{@code limit}个标签
     * @param limit 返回个数
     * @return 热度前limit个标签
     */
    List<Long> findHotsTagIds(int limit);

    /**
     * 根据tag_id返回tag
     * @param tagIds 查询的tag_id
     * @return 返回对应tag
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
