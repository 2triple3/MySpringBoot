package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.ProductAttribute;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductAttributeService extends IService<ProductAttribute> {
    void updateByMyId(Integer attributeId);

    List<Attribute> getAttributeByProduct(Integer productId, Integer categoryId);

    void saveProductAttribute(List<ProductAttribute> list);

    void deleteProductAttribute(Integer productId);

}
