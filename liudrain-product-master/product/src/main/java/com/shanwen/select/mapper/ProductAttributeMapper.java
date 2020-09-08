package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.entity.ProductAttribute;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface ProductAttributeMapper extends BaseMapper<ProductAttribute> {
    void updateByMyId(Integer id);

    List<AttributeClass> getAttributeClass(Integer productId);


    List<Attribute> getAttributeByProduct(Integer productId, Integer categoryId);


    void saveProductAttribute(List<ProductAttribute> list);

    void deleteProductAttribute(Integer productId);

}
