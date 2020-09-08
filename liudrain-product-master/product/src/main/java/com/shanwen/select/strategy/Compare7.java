package com.shanwen.select.strategy;

import com.shanwen.select.entity.Decision;
import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("7")
public class Compare7 implements CompareStrategy {
    //隐藏相同+隐藏无内容+高亮差异
    @Override
    public List<Product> doCompare(ProductDecisionMapper productDecisionMapper, Map map, List<Product> focusList) {
        focusList.forEach(product -> {
            map.put("productId", product.getProductId());
            List<DecisionClass> decisionClasses = productDecisionMapper.getDecisionClassHideSameAndNoContent(map);
            // List<AttributeClass> attributeClasses = productAttributeClassService.getAttributeClass(product.getProductId());
            // product.setAttributeClasses(attributeClasses);
            decisionClasses.forEach(decisionClass -> {
                List<Decision> decisions = decisionClass.getDecisionList();
                for (int i = 0; i < decisions.size(); i++) {
                    decisions.get(i).setActive(1);
                }
                decisionClass.setDecisionList(decisions);
            });
            product.setDecisions(decisionClasses);
        });
        return focusList;
    }
}
