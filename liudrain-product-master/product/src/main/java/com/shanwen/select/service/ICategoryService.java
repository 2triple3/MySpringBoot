package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Category;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface ICategoryService extends IService<Category> {
    void updateByMyId(Integer categoryId);

    //查找修改的一条记录
    List<Category> selectAllNeed(Category category);

    //修改
    void updateAll(Category category);

    void updateName(Category category);

    List<Category> selectAll(Category category);

    List<Category> selectForProductAdd();


}
