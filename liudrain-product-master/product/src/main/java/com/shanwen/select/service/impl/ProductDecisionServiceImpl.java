package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.ProductDecision;
import com.shanwen.select.mapper.ProductDecisionMapper;
import com.shanwen.select.service.IProductDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class ProductDecisionServiceImpl extends ServiceImpl<ProductDecisionMapper, ProductDecision> implements IProductDecisionService {
    @Autowired
    ProductDecisionMapper productDecisionMapper;

    @Override
    public void updateByMyId(Integer decisionId) {
        productDecisionMapper.updateByMyId(decisionId);

    }
}
