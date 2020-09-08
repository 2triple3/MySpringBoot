package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 查询参数封装实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QueryParams {
    //品牌
    private String brandtext;
    //价格最高
    private Integer heigh;
    //价格最低
    private Integer low;

    //参数
    private String params;
    //排序方式
    private Integer logicSort;
}
