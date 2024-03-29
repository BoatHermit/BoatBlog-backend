package com.boathermit.boatblog.service;

import com.boathermit.boatblog.model.vo.CategoryVo;
import com.boathermit.boatblog.utils.Result;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:36
 */
public interface CategoryService {

    /**
     * 通过id查找
     * @param id 类别id
     * @return 类别
     */
    CategoryVo findCategoryById(Long id);

    /**
     * 查看所有文章类别
     * @return 类别
     */
    Result findAll();
}
