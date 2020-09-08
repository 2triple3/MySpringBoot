package com.shanwen.select.strategy;

import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("3")
public class Compare3 implements CompareStrategy {
    //隐藏无内容+隐藏相同
    @Override
    public List<Product> doCompare(ProductDecisionMapper productDecisionMapper, Map map, List<Product> focusList) {
        focusList.forEach(product -> {

            map.put("productId", product.getProductId());
            List<DecisionClass> decisionClasses = productDecisionMapper.getDecisionClassHideSameAndNoContent(map);
            // List<AttributeClass> attributeClasses = productAttributeClassService.getAttributeClass(product.getProductId());
            // product.setAttributeClasses(attributeClasses);
            product.setDecisions(decisionClasses);
        });
        return focusList;
    }
}
