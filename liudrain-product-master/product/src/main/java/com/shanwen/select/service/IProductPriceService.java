package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.ProductPrice;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductPriceService extends IService<ProductPrice> {
    void updateByMyId(Integer productPriceId);

    //关联product查询
    List<ProductPrice> selectAll(ProductPrice productPrice);

    //自定义新增--保存功能
    void saveAll(ProductPrice productPrice);

    void updateAll(ProductPrice productPrice);

    void deleteById(Integer productPriceId);

    //通过id查询实体
    ProductPrice selectPriceById(Integer productPriceId);

    List<ProductPrice> selectAllNeed(ProductPrice productPrice);
}
