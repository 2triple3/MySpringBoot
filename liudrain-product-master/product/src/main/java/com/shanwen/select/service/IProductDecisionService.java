package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.entity.ProductDecision;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductDecisionService extends IService<ProductDecision> {
    void updateByMyId(Integer decisionId);

}

