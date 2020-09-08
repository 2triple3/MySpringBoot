package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.DecisionClass;

import java.util.List;

public interface IDecisionClassService extends IService<DecisionClass> {

    List<DecisionClass> selectAll(Integer categoryId);
    //新增
    void insertAll(DecisionClass decisionClass);

    void updateByMyId(Integer decisionClassId);

    DecisionClass selectMyNeed(Integer decisionClassId);

    void updateAll(DecisionClass decisionClass);



}
