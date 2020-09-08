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
@TableName("product_Decision")
public class ProductDecision implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品主键
     */
    private Product product;

    /**
     * 决策参数相关描述信息
     */
    private Decision decision;

    /**
     * 评价
     */
    private String result;

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
