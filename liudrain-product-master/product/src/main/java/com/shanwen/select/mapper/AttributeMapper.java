package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.AttributeClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品属性 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface AttributeMapper extends BaseMapper<Attribute> {

    void updateByMyId(Integer attributeId);


    List<Attribute> selectByMyId(Attribute attribute);

    void insertAll(List<Attribute> list);

    Attribute selectMyNeed(Integer attributeId);

    void updateAll(Attribute attribute);

    void deleteByAttributeClass(Integer attributeClassId);

    //通过类别查询出详情的排序规则
    List<Attribute> getAttributeForSort(Integer categoryId);
}
