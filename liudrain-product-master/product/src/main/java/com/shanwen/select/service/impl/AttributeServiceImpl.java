package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.mapper.AttributeMapper;
import com.shanwen.select.service.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements IAttributeService {
    @Autowired
    AttributeMapper attributeMapper;

    @Override
    public void updateByMyId(Integer attributeId) {

        attributeMapper.updateByMyId(attributeId);
    }


    @Override
    public List<Attribute> selectByMyId(Attribute attribute) {

        List<Attribute> list = attributeMapper.selectByMyId(attribute);
        return list;
    }

    @Override
    public void insertAll(List<Attribute> attribute) {
        attributeMapper.insertAll(attribute);
    }

    @Override
    public Attribute selectMyNeed(Integer attributeId) {
        return attributeMapper.selectMyNeed(attributeId);
    }

    @Override
    public void updateAll(Attribute attribute) {
        attributeMapper.updateAll(attribute);
    }

    @Override
    public void deleteByAttributeClass(Integer attributeClassId) {
        attributeMapper.deleteByAttributeClass(attributeClassId);
    }

    @Override
    public List<Attribute> getAttributeForSort(Integer categoryId) {
        return attributeMapper.getAttributeForSort(categoryId);
    }


}
