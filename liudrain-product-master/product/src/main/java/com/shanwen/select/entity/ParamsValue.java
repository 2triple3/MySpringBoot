package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("params_value")
public class ParamsValue {

    private Integer pvId;
    private String pvName;
    private String value;

    /**
     * 首字母  品牌选择使用
     */
    private String firstLetter;
    /**
     * 是否显示 品牌选择使用
     */
    private boolean isshow = false;

    private boolean checked = false;

}
