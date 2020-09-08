package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.AttributeClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 属性分类 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface AttributeClassMapper extends BaseMapper<AttributeClass> {

    List<AttributeClass> selectAll(Integer categoryId);

    void insertAll(AttributeClass attributeClass);

    void updateByMyId(Integer attributeClassId);

    AttributeClass selectMyNeed(Integer attributeClassId);

    void updateAll(AttributeClass attributeClass);
}
