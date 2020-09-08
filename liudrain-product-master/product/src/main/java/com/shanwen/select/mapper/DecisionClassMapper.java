package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.shanwen.select.entity.DecisionClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface DecisionClassMapper extends BaseMapper<DecisionClass> {

    List<DecisionClass> selectAll(Integer categoryId);

    void insertAll(DecisionClass decisionClass);

    void updateByMyId(Integer decisionClassId);

    DecisionClass selectMyNeed(Integer decisionClassId);

    void updateAll(DecisionClass decisionClass);
}
