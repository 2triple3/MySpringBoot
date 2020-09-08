package com.shanwen.select.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 产品的优点和缺点
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Feature {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer featureId;
    /**
     * lid
     */
    private Integer categoryId;
    /**
     * 标志位  1是优点  2是缺点
     */
    private Integer flag;
    /**
     * 描述
     */
    private String content;
}
