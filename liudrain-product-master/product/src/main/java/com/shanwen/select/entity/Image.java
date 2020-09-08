package com.shanwen.select.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 产品图片
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Image {

    /**
     * 产品图片id
     */

    @TableId(value = "image_id", type = IdType.AUTO)
    private Integer imageId;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 显示在轮播图
     */
    private Integer type;

    /**
     * 显示在轮播图
     */
    private Integer listImg;
    /**
     * 图片url
     */
    private String imageUrl;

    /**
     * 排序，数字越大越靠前
     */
    private Integer sort;
}
