package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.ProductPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface ProductPriceMapper extends BaseMapper<ProductPrice> {
    void updateByMyId(Integer productPriceId);

    List<ProductPrice> selectAll(ProductPrice productPrice);

    void saveAll(ProductPrice productPrice);

    void updateAll(ProductPrice productPrice);

    void deleteById(Integer productPriceId);

    //通过id查询实体
    ProductPrice selectPriceById(Integer productPriceId);

    List<ProductPrice> selectAllNeed(ProductPrice productPrice);
}
