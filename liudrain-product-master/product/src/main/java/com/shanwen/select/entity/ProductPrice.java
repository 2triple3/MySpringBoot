package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_Price")
public class ProductPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品价格id
     */
    @TableId(value = "product_price_id", type = IdType.AUTO)
    private Integer productPriceId;

    /**
     * 产品id
     */
    private Product product;

    /**
     * 价格数目
     */
    private Double priceNum;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 状态
     */
    private Integer status;


}
