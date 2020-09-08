package com.shanwen.select.strategy;

import com.shanwen.select.entity.Decision;
import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.ProductDecisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("4")
public class Compare4 implements CompareStrategy {
    //高亮差异
    @Override
    public List<Product> doCompare(ProductDecisionMapper productDecisionMapper, Map map, List<Product> focusList) {
        focusList.forEach(product -> {
            List<DecisionClass> decisionClasses = productDecisionMapper.getDecisionClass(product.getProductId());
            // List<AttributeClass> attributeClasses = productAttributeClassService.getAttributeClass(product.getProductId());
            // product.setAttributeClasses(attributeClasses);
            map.put("productId", product.getProductId());
            List<DecisionClass> decisionClassHideSame = productDecisionMapper.getDecisionClassHideSame(map);

            for (int i = 0; i < decisionClasses.size(); i++) {
                List<Decision> decisions = decisionClasses.get(i).getDecisionList();
                for (int j = 0; j < decisions.size(); j++) {
                    for (int k = 0; k < decisionClassHideSame.size(); k++) {
                        List<Decision> decisions2 = decisionClassHideSame.get(k).getDecisionList();
                        for (int l = 0; l < decisions2.size(); l++) {
                            if (decisions.get(j).getDecisionId() == decisions2.get(l).getDecisionId()) {
                                decisions.get(j).setActive(1);
                            }
                        }
                    }
                }
                decisionClasses.get(i).setDecisionList(decisions);
            }
            product.setDecisions(decisionClasses);
        });
        return focusList;
    }
}
