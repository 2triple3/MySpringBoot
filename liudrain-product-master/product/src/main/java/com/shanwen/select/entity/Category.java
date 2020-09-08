package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品分类id
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 名称
     */
    @TableField(value = "c_name")
    //private String cName;这样的命名方式，mybatis对应不上，前台list.cName出不来
    private String cname;

    /**
     * 产品级别
     */
    private Integer level;

    /**
     * 父id
     */
    private Integer parent;


    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 子类别
     */
    @TableField(exist = false)
    private List<Category> categoryList;
    /**
     * 决策参数,一对多关系
     */
    @TableField(exist = false)
    private List<Decision> decisons;
    /**
     * 电气参数，一对多关系
     */
    @TableField(exist = false)
    private List<AttributeClass> attributeClassz;

    public Category() {

    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
