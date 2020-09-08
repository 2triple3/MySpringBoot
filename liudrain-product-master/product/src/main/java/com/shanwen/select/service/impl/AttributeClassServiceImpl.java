package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.mapper.AttributeClassMapper;
import com.shanwen.select.mapper.AttributeMapper;
import com.shanwen.select.service.IAttributeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 属性分类 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class AttributeClassServiceImpl extends ServiceImpl<AttributeClassMapper, AttributeClass> implements IAttributeClassService {

    @Autowired
    AttributeClassMapper attributeClassMapper;

    @Override
    public List<AttributeClass> selectAll(Integer categoryId) {
        List<AttributeClass> list = attributeClassMapper.selectAll(categoryId);
        return list;
    }

    @Override
    public void insertAll(AttributeClass attributeClass) {
        attributeClassMapper.insertAll(attributeClass);
    }

    @Override
    public void updateByMyId(Integer attributeClassId) {
        attributeClassMapper.updateByMyId(attributeClassId);
    }

    @Override
    public AttributeClass selectMyNeed(Integer attributeClassId) {

        return attributeClassMapper.selectMyNeed(attributeClassId);
    }

    @Override
    public void updateAll(AttributeClass attributeClass) {
        attributeClassMapper.updateAll(attributeClass);
    }


//

}
