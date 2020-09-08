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
 * @since 2019-12-28
 */
public interface IProductAttributeClassService extends IService<AttributeClass> {


    List<AttributeClass> getAttributeClass(Integer productId);
}
