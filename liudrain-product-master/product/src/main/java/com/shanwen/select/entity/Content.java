package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 有问必答吗，购买页面说明
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Content {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer contentId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 内容详情
     */
    private String content;

}
