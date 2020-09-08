package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.ProductAttribute;
import com.shanwen.select.mapper.ProductAttributeMapper;
import com.shanwen.select.service.IProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements IProductAttributeService {
    @Autowired
    ProductAttributeMapper productAttributeMapper;

    @Override
    public void updateByMyId(Integer attributeId) {
        productAttributeMapper.updateByMyId(attributeId);
    }

    @Override
    public List<Attribute> getAttributeByProduct(Integer productId, Integer categoryId) {
        return productAttributeMapper.getAttributeByProduct(productId, categoryId);
    }

    @Override
    public void saveProductAttribute(List<ProductAttribute> list) {
        productAttributeMapper.saveProductAttribute(list);
    }

    @Override
    public void deleteProductAttribute(Integer productId) {
        productAttributeMapper.deleteProductAttribute(productId);

    }
}
