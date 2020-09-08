package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("params")
public class Params {
    private Integer pId;
    private String pName;
    private List<ParamsValue> paramsValues;
}
