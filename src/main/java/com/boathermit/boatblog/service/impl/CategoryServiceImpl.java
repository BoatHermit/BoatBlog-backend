package com.boathermit.boatblog.service.impl;

import com.boathermit.boatblog.dao.CategoryMapper;
import com.boathermit.boatblog.model.po.Category;
import com.boathermit.boatblog.model.vo.CategoryVo;
import com.boathermit.boatblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yin Zihang
 * @since 2022/7/29 12:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryVo findCategoryById(Long id) {
        Category category =  categoryMapper.selectById(id);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
