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
 * 决策参数一级目录
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DecisionClass implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 决策参数分类id
     */
    @TableId(value = "decision_class_id", type = IdType.AUTO)
    private Integer decisionClassId;


    private Integer decisionClassCateId;

    /**
     * 方便使用mp
     */
    @TableField(exist = false)
    private DecisionClassCate decisionClassCate;

    /**
     * 决策参数分类名称
     */
    @TableField("dcName")
    private String dcName;
    /**
     * 决策参数说明
     */
    private String description;

    /**
     * 产品类型
     */

    @TableField(exist = false)
    private Category category;

    /**
     * 创建时间
     */
    @TableField("createDate")
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer dcsort;


    /**
     * 分数
     */
    @TableField(exist = false)
    private Float score;

    /**
     * 小程序使用参数，默认参数隐藏，点击在显示
     */
    @TableField(exist = false)
    private Boolean hidden = false;


    /**
     * 产品属性
     */
    @TableField(exist = false)
    private List<Decision> decisionList;


}
