package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品信息
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品品牌
     */
    private String brand;

    /**
     * 产品型号
     */
    private String model;

    /**
     * 产品分类
     */
    private Category category;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 产品排序，数字越大，越推荐
     */
    private Integer sort;

    /**
     * 颜色级别
     */
    private Integer colorLevel;
    /**
     * 产品概述。文字描述相关内容
     */
    private String overview;

    /**
     * 产品评级。推荐，一般，良好，糟糕
     */
    private String rate;
    /**
     * 产品评分 ，1-10分
     */
    private Float score;
    /**
     * 产品星级 1-5星
     */
    private Float star;

    /**
     * 当前人员是否关注
     */
    private Integer focus;
    /**
     * 优点
     */
    private List<Feature> advantages;
    /**
     * 缺点
     */
    private List<Feature> disadvantages;


    /**
     * 产品图片
     */
    private List<Image> imageList;


    /**
     * 关联对象list，产品属性list
     */
    private List<AttributeClass> attributeClasses;

    /**
     * 关联对象list，决策参数
     */
    private List<DecisionClass> decisions;
    /**
     * 关联对象list，产品价格
     */
    private List<ProductPrice> productPrices;

    /**
     * 关联对象list，产品评价
     */
    private List<ProductReport> productReports;


}
