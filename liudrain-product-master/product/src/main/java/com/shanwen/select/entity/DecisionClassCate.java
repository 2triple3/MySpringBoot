package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 决策参数分类一级类别
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class DecisionClassCate {

    /**
     * 决策参数分类类别
     */
    @TableId(value = "decision_class_cate_id", type = IdType.AUTO)
    private Integer decisionClassCateId;

    /**
     * 类别
     */
    @TableField(exist = false)
    private Category category;

    /**
     * 类别id
     */
    private Integer categoryId;

    /**
     * 决策参数一级分类说明
     */

    private String dccName;
    /**
     * 决策参数 一级分类说明
     */
    private String dccDescription;

    /**
     * 二级分类
     */
    @TableField(exist = false)
    List<DecisionClass> decisionClassList;
}
