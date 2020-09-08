package com.shanwen.select.entity;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数关联表（关联产品和属性）
 * </p>
 *
 * @author liudong
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_attribute")
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性的主键
     */
    @TableField("attribute_id")
    private Integer attributeId;

    /**
     * 产品的主键
     */
    @TableField("productId")
    private Integer productId;

    /**
     * 参数描述
     */
    private String value;

    /**
     * 状态
     */
    private Integer status;


}
