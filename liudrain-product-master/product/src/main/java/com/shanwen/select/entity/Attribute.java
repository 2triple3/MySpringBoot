package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品属性
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId(value = "attribute_id", type = IdType.AUTO)
    private Integer attributeId;

    /**
     * 属性分类id
     */
    private AttributeClass attributeClass;

    /**
     * 属性名称
     */
    @TableField("a_name")
    private String aname;


    /**
     * 属性值   在数据库中没有这个值 值存在product_attribute
     */
    private String value;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否是作为筛选条件
     */
    private Integer filtrate;

    /**
     * 作为排序选项
     */
    private Integer sortOptions;


    /**
     * 状态
     */
    private Integer status;


}
