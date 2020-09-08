package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 作为排序字符
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Filtrate {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer filtrateId;
    /**
     * 品类id
     */
    private Integer categoryId;
    /**
     * 参数id
     */
    private Integer paramId;

    /**
     * 参数类型
     */
    private Integer paramType;
    /**
     * 筛选/排序参数名称
     */
    private String filtrateName;

    /**
     * 显示顺序排序
     */
    private Integer sort;

    /**
     * 数据库对应的表字段
     */
    private String dbName;


}
