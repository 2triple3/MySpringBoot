package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品决策参数详情
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_DecisionClass")
public class ProductDecisionClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品主键
     */
    private Product product;

    /**
     * 决策参数分类
     */
    private DecisionClass decisionClass;

    /**
     * 分数
     */
    private Float score;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;


}
