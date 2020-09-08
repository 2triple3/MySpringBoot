package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Category;
import com.shanwen.select.mapper.CategoryMapper;
import com.shanwen.select.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void updateByMyId(Integer productClassId) {

        categoryMapper.updateByMyId(productClassId);
    }

    @Override
    public List<Category> selectAllNeed(Category category) {
        List<Category> categoryList = categoryMapper.selectAllNeed(category);
        return categoryList;
    }

    @Override
    public void updateAll(Category category) {
        categoryMapper.updateAll(category);
    }

    @Override
    public void updateName(Category category) {
        categoryMapper.updateName(category);
    }

    @Override
    public List<Category> selectAll(Category category) {
        List<Category> categoryList = categoryMapper.selectAll(category);
        return categoryList;
    }

    @Override
    public List<Category> selectForProductAdd() {
        return categoryMapper.selectForProductAdd();
    }


}
