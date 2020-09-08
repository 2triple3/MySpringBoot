package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sort {

    /**
     * 小程序排序id
     */
    @TableId(type = IdType.AUTO)
    private Integer sortId;

    private Integer categoryId;
    /**
     * 数据储存id
     */
    private Integer filtrateId;
    /**
     * 筛选/排序参数名称
     */
    private String showName;


    /**
     * 升降序选择
     */
    private String way;

}