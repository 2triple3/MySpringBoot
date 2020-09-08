package com.shanwen.select.strategy;

import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;

import java.util.List;
import java.util.Map;

/**
 * 对比的方法对应的策略
 */
public interface CompareStrategy {

    /**
     * @param productDecisionMapper 决策参数的mapper
     * @param map                   关注的产品的ids
     * @param focusList             关注的产品
     * @return
     */
    List<Product> doCompare(ProductDecisionMapper productDecisionMapper, Map map, List<Product> focusList);


}
