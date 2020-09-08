package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.shanwen.select.entity.Product;
import com.shanwen.select.entity.ProductPrice;
import com.shanwen.select.mapper.ProductMapper;
import com.shanwen.select.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> selectFocusProduct(String userId) {
        return productMapper.selectFocusProduct(userId);
    }

    @Override
    public List<Product> selectAll(Product product) {
        return productMapper.selectAll(product);
    }

    @Override
    public List<Product> getQuery(String name) {
        return productMapper.getQuery(name);
    }

    @Override
    public List<Product> getQueryListByUser(Map map) {


        List<Product> result = productMapper.getQueryListByUser(map);

        return result;
    }

    @Override
    public Product getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }

    @Override
    public int updateFocus(Map map, Integer type) {

        if (type == 1) {
            return productMapper.insertFocus(map);

        }
        if (type == 2) {
            return productMapper.deleteFocus(map);
        }
        return 0;
    }

    @Override
    public void updateAll(Product product) {
        productMapper.updateAll(product);
    }


    @Override
    public void insertAll(Product product) {
        productMapper.insertAll(product);
    }

    @Override
    public List<Map> getBrand() {
        return productMapper.getBrand();
    }

    @Override
    public List<Product> selectProductByMap(Map map) {
        return productMapper.selectProductByMap(map);
    }

    /* @Override
    public IPage<Product> selectPage1(Page<Product> page, QueryWrapper<Product> queryWrapper) {

        return productMapper.selectPage1(page,queryWrapper);
    }*/


}