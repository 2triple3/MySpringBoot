package com.shanwen.select.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 产品特点参数详情
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_Feature")
public class ProductFeature {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_feature_id", type = IdType.AUTO)
    private Integer productFeatureId;
    /**
     * 产品主键
     */
    private Integer productId;
    /**
     * 特点
     */
    private Integer featureId;

    @TableField(exist = false)
    private Product product;


    @TableField(exist = false)
    private Feature feature;

}
