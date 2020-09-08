package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.mapper.AttributeClassMapper;
import com.shanwen.select.mapper.ProductAttributeClassMapper;
import com.shanwen.select.mapper.ProductAttributeMapper;
import com.shanwen.select.service.IProductAttributeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 属性分类 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2019-12-28
 */
@Service
public class ProductAttributeClassServiceImpl extends ServiceImpl<ProductAttributeClassMapper, AttributeClass> implements IProductAttributeClassService {

    @Autowired
    ProductAttributeMapper productAttributeMapper;

    @Override
    public List<AttributeClass> getAttributeClass(Integer productId) {
        return productAttributeMapper.getAttributeClass(productId);
    }
}
