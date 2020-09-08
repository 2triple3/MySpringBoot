package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 属性分类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AttributeClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性分类id
     */
    @TableId(value = "attribute_class_id", type = IdType.AUTO)
    private Integer attributeClassId;

    /**
     * 属性分类名称
     */
    private String acName;
    /**
     * 产品大类型
     */
    @TableField(exist = false)
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
     * 状态
     */
    private Integer sort;


    /**
     * 产品属性
     */
    @TableField(exist = false)
    private List<Attribute> attributeList;
}
