package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口 产品和决策参数对应表
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */

public interface ProductDecisionMapper extends BaseMapper<ProductDecision> {
    void updateByMyId(Integer decisionId);

    List<DecisionClass> getDecisionClass(Integer productId);

    List<DecisionClass> getDecisionClassForWeb(Integer productId, Integer categoryId);

    //隐藏相同
    List<DecisionClass> getDecisionClassHideSame(Map map);

    //隐藏无内容
    List<DecisionClass> getDecisionClassNoContent(Map map);


    //隐藏相同 +隐藏无内容
    List<DecisionClass> getDecisionClassHideSameAndNoContent(Map map);


    void deleteDecisionClass(Integer productId);

    void saveDecisionClass(List<ProductDecisionClass> productDecisionClassList);

    void deleteDecision(Integer productId);

    void saveDecision(List<ProductDecision> productDecisionList);

    List<Decision> getDecisionResult(Integer categoryId);
}
