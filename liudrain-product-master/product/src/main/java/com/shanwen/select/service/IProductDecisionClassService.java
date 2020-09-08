package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.ProductDecision;
import com.shanwen.select.entity.ProductDecisionClass;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductDecisionClassService extends IService<ProductDecisionClass> {
    List<DecisionClass> getDecisionClass(Integer productId);

    //隐藏相同
    List<DecisionClass> getDecisionClassHideSame(Map map);

    //隐藏无内容
    List<DecisionClass> getDecisionClassNoContent(Map map);

    List<DecisionClass> getDecisionClassForWeb(Integer productId, Integer categoryId);


    void deleteDecisionClass(Integer productId);

    void saveDecisionClass(List<ProductDecisionClass> productDecisionClassList);

    void deleteDecision(Integer productId);

    void saveDecision(List<ProductDecision> productDecisionList);

    List<String> getDecisionResult(Integer categoryId);
}
