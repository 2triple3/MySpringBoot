package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    void updateByMyId(Integer id);

    List<Category> selectAllNeed(Category category);

    void updateAll(Category category);

    void updateName(Category category);

    List<Category> selectAll(Category category);

    //选择最末级分类
    List<Category> selectForProductAdd();
}
