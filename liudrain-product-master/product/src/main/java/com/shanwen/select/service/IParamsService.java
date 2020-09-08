package com.shanwen.select.service;

import com.shanwen.select.entity.Params;

import java.util.List;

public interface IParamsService {
    /*选择出电气参数的筛选*/
    List<Params> selectParams();

    /*选择决策指标的筛选*/
    List<Params> selectParams2();
}
