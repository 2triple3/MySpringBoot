package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.AttributeClass;

import java.util.List;

/**
 * <p>
 * 产品属性 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IAttributeService extends IService<Attribute> {
    void updateByMyId(Integer attributeId);

    //根据主表attributeClass的主键查找明细表中详细的参数类型
    List<Attribute> selectByMyId(Attribute attribute);

    void insertAll(List<Attribute> attribute);

    Attribute selectMyNeed(Integer attributeId);

    void updateAll(Attribute attribute);


    void deleteByAttributeClass(Integer attributeClassId);

    //通过类别查询出详情的排序规则
    List<Attribute> getAttributeForSort(Integer categoryId);

}
