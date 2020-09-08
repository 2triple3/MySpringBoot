package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Decision;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品决策参数 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface DecisionMapper extends BaseMapper<Decision> {
    void updateByMyId(Integer decisionId);

    List<Decision> selectAll(Decision decision);

    void insertAll(List<Decision> list);

    Decision selectMyNeed(Integer decisionId);

    void updateAll(Decision decision);

    void deleteByDecisionClass(Integer decisionClassId);
}
