package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.*;
import com.shanwen.select.mapper.ProductAttributeClassMapper;
import com.shanwen.select.mapper.ProductDecisionClassMapper;
import com.shanwen.select.mapper.ProductDecisionMapper;
import com.shanwen.select.service.IProductAttributeClassService;
import com.shanwen.select.service.IProductDecisionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductDecisionClassServiceImpl extends ServiceImpl<ProductDecisionClassMapper, ProductDecisionClass>
        implements IProductDecisionClassService {

    @Autowired
    ProductDecisionMapper productDecisionMapper;

    @Override
    public List<DecisionClass> getDecisionClass(Integer productId) {
        return productDecisionMapper.getDecisionClass(productId);
    }

    @Override
    public List<DecisionClass> getDecisionClassHideSame(Map map) {
        return productDecisionMapper.getDecisionClassHideSame(map);
    }

    @Override
    public List<DecisionClass> getDecisionClassNoContent(Map map) {
        return productDecisionMapper.getDecisionClassNoContent(map);
    }

    @Override
    public List<DecisionClass> getDecisionClassForWeb(Integer productId, Integer categoryId) {
        return productDecisionMapper.getDecisionClassForWeb(productId, categoryId);
    }

    @Override
    public void deleteDecisionClass(Integer productId) {
        productDecisionMapper.deleteDecisionClass(productId);
    }

    @Override
    public void saveDecisionClass(List<ProductDecisionClass> productDecisionClassList) {
        productDecisionMapper.saveDecisionClass(productDecisionClassList);
    }

    @Override
    public void deleteDecision(Integer productId) {
        productDecisionMapper.deleteDecision(productId);
    }

    @Override
    public void saveDecision(List<ProductDecision> productDecisionList) {
        productDecisionMapper.saveDecision(productDecisionList);
    }

    @Override
    public List<String> getDecisionResult(Integer categoryId) {

        List<Decision> decisions = productDecisionMapper.getDecisionResult(categoryId);


        List<String> strings = new ArrayList<>();
        for (int i = 0; i < decisions.size(); i++) {
            strings.add(decisions.get(i).getResult());
        }
        return strings;
    }
}