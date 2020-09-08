package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 付款订单
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sworder")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单号码
     */

    private String orderNum;

    /**
     * 用户id
     */
    private WeixinUserInfo userInfo;

    /**
     * 支付价格
     */
    private Double payPrice;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;


    /**
     * 购买时长
     */
    private Integer dateLength;


    /**
     * 订单状态。0是未支付。1 表示成功
     */
    private Integer status;
}
