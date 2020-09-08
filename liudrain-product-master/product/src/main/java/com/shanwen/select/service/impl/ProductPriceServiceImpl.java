package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.ProductPrice;
import com.shanwen.select.mapper.ProductPriceMapper;
import com.shanwen.select.service.IProductPriceService;
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
public class ProductPriceServiceImpl extends ServiceImpl<ProductPriceMapper, ProductPrice> implements IProductPriceService {

    @Autowired
    ProductPriceMapper productPriceMapper;

    @Override
    public void updateByMyId(Integer productPriceId) {
        productPriceMapper.updateByMyId(productPriceId);
    }

    @Override
    public List<ProductPrice> selectAll(ProductPrice productPrice) {
        List<ProductPrice> list = productPriceMapper.selectAll(productPrice);

        return list;
    }

    @Override
    public void saveAll(ProductPrice productPrice) {
        productPriceMapper.saveAll(productPrice);
    }

    @Override
    public void updateAll(ProductPrice productPrice) {
        productPriceMapper.updateAll(productPrice);
    }

    @Override
    public void deleteById(Integer productPriceId) {
        productPriceMapper.deleteById(productPriceId);
    }

    @Override
    public ProductPrice selectPriceById(Integer productPriceId) {
        return productPriceMapper.selectPriceById(productPriceId);
    }

    @Override
    public List<ProductPrice> selectAllNeed(ProductPrice productPrice) {
        List<ProductPrice> list = productPriceMapper.selectAllNeed(productPrice);
        return list;
    }
}
