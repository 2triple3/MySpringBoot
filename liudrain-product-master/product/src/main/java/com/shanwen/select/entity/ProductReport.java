package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 产品报告
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_Report")
public class ProductReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品报告id
     */
    @TableId(value = "product_report_id", type = IdType.AUTO)
    private Integer productReportId;

    /**
     * 产品id
     */
   // private Product productId;
    private Product product;

    /**
     * 产品报告模块名称
     */
    private String name;

    /**
     * 产品报告模块内容
     */
    //private Blob content;
    private String content;
    /**
     * 产品报告标签
     */
    private String tag;

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
