package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Decision;
import com.shanwen.select.mapper.DecisionMapper;
import com.shanwen.select.service.IDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品决策参数 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class DecisionServiceImpl extends ServiceImpl<DecisionMapper, Decision> implements IDecisionService {
    @Autowired
    DecisionMapper decisionMapper;

    @Override
    public List<Decision> selectAll(Decision decision) {
        List<Decision> list = decisionMapper.selectAll(decision);
        return list;
    }

    @Override
    public void updateByMyId(Integer decisionId) {
        decisionMapper.updateByMyId(decisionId);
    }

    @Override
    public void insertAll(List<Decision> decision) {
        decisionMapper.insertAll(decision);
    }

    @Override
    public Decision selectMyNeed(Integer decisionId) {
        return decisionMapper.selectMyNeed(decisionId);
    }

    @Override
    public void updateAll(Decision decision) {
        decisionMapper.updateAll(decision);
    }

    @Override
    public void deleteByDecisionClass(Integer decisionClassId) {
        decisionMapper.deleteByDecisionClass(decisionClassId);
    }

//    @Override
//    public List<Decision> selectAll(Decision decision) {
//        List<Decision> list=decisionMapper.selectAll(decision);
//        return list;
//    }
}
