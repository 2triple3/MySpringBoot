package com.shanwen.select.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.extension.service.IService;

import com.shanwen.select.entity.Product;
import com.shanwen.select.entity.ProductPrice;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductService extends IService<Product> {
    // 通过userId查询用户关注的产品列表
    List<Product> selectFocusProduct(String userId);

    //查询所有数据
    List<Product> selectAll(Product product);

    List<Product> getQuery(String name);

    //小程序查询
    List<Product> getQueryListByUser(Map map);

    Product getProductById(Integer productId);

    //新增/取消关注
    int updateFocus(Map map, Integer type);

    void updateAll(Product product);

    //新增
    void insertAll(Product product);

    //查询所有品牌用于查询使用
    List<Map>  getBrand();

    List<Product> selectProductByMap(Map map);
    //自定义分页
    //IPage<Product> selectPage1(Page<Product> page, QueryWrapper<Product> queryWrapper);
}
