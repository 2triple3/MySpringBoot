package com.shanwen.select.config;

import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;
import com.shanwen.select.strategy.CompareStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StrategyContext {
    @Autowired
    private final Map<String, CompareStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    ProductDecisionMapper productDecisionMapper;

    public StrategyContext(Map<String, CompareStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    //策略选择
    public List<Product> getResource(List<Product> focusList, String strategyId) {
        //得到待处理的产品list的ids
        StringBuilder stringBuilder = new StringBuilder();
        focusList.forEach(product -> {
            stringBuilder.append(product.getProductId() + ",");
        });
        String productIds = stringBuilder.toString();
        Map map = new HashMap();
        productIds = productIds.substring(0, productIds.length() - 1);
        map.put("productIds", productIds);

        return strategyMap.get(strategyId).doCompare(productDecisionMapper, map, focusList);
    }
}
