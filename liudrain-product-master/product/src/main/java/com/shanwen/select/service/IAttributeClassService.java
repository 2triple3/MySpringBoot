package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.AttributeClass;

import java.util.List;

/**
 * <p>
 * 属性分类 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IAttributeClassService extends IService<AttributeClass> {

    List<AttributeClass> selectAll(Integer categoryId);
    //增加功能的接口
    void insertAll(AttributeClass attributeClass);
    //伪删除
    void updateByMyId(Integer attributeClassId);

    AttributeClass selectMyNeed(Integer attributeClassId);
    //修改
    void updateAll(AttributeClass attributeClass);
}
