package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.github.pagehelper.Page;
import com.shanwen.select.entity.Product;
import com.shanwen.select.entity.ProductPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface ProductMapper extends BaseMapper<Product> {

    // 通过userId查询用户关注的产品列表
    List<Product> selectFocusProduct(String userId);

    List<Product> selectAll(Product product);

    //小程序查询功能
    List<Product> getQuery(String name);

    /**
     * 通过id查询product
     *
     * @param productId
     * @return Product
     */
    Product getProductById(Integer productId);

    //自定义修改
    void updateAll(Product product);


    //小程序查询功能
    List<Product> getQueryListByUser(Map map);

    //增加关注
    int insertFocus(Map map);

    //取消关注
    int deleteFocus(Map map);

    //新增
    void insertAll(Product product);

    //查询所有品牌用于查询使用
    List<Map> getBrand();


    List<Product> selectProductByMap(Map map);
    //自定义分页

    //IPage<Product> selectPage(Page page, @Param(Constants.WRAPPER) Wrapper<Product> queryWrapper);

    //IPage<Product> selectPage1(Page<Product> page,@Param(Constants.WRAPPER) QueryWrapper<Product> queryWrapper);
}
