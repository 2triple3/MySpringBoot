package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购买会员价格 不是当前的产品价格
 * </p>
 *
 * @author liudong
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员价格id
     */
    @TableId(value = "price_id", type = IdType.AUTO)
    private Integer priceId;

    /**
     * 会员商品名称
     */
    private String name;

    /**
     * 价格数目，单位元
     */
    private Double priceNum;

    /**
     * 时长 单位是天数
     */
    private Integer duration;

    /**
     * 创建时间
     */
    private Date createDate;


}
