package com.boathermit.boatblog.model.param;

import com.boathermit.boatblog.model.vo.CategoryVo;
import com.boathermit.boatblog.model.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author Yin Zihang
 * @since 2022/8/2 15:30
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
