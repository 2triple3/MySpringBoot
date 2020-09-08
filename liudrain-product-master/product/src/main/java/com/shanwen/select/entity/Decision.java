package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

/**
 * <p>
 * 产品决策参数
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Decision implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 决策参数id
     */
    @TableId(value = "decision_id", type = IdType.AUTO)
    private Integer decisionId;


    /**
     * 决策参数父id
     */
    private DecisionClass decisionClass;

    /**
     * 决策参数名称
     */
    private String dname;

    /**
     * 决策参数说明（预留可能会用到）
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否是作为筛选条件
     */
    private Integer filtrate;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer dsort;

    /**
     * 评价完成情况（不在数据库中做映射，仅供查询使用）
     */
    private String result;

    /**
     * 小程序高亮差异使用，不在数据库中做映射
     */
    private Integer active;
}
