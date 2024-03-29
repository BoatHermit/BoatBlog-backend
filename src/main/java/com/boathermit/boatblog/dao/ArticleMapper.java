package com.boathermit.boatblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boathermit.boatblog.model.po.Article;
import com.boathermit.boatblog.model.vo.ArchivesVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yinzihang
 * @since 0.1
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 返回归档列表
     * @return 归档列表
     */
    List<ArchivesVo> listArchives();
}
