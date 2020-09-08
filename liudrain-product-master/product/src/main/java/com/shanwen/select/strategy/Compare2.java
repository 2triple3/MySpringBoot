package com.shanwen.select.strategy;

import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("2")
public class Compare2 implements CompareStrategy {
    //隐藏无内容
    @Override
    public List<Product> doCompare(ProductDecisionMapper productDecisionMapper, Map map, List<Product> focusList) {
        focusList.forEach(product -> {
            map.put("productId", product.getProductId());
            List<DecisionClass> decisionClasses = productDecisionMapper.getDecisionClassNoContent(map);
            // List<AttributeClass> attributeClasses = productAttributeClassService.getAttributeClass(product.getProductId());
            // product.setAttributeClasses(attributeClasses);
            product.setDecisions(decisionClasses);
        });
        return focusList;
    }


}
