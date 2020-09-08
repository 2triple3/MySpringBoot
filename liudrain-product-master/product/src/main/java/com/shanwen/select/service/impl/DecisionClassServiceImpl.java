package com.shanwen.select.service.impl;




import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.DecisionClass;
import com.shanwen.select.mapper.DecisionClassMapper;
import com.shanwen.select.service.IDecisionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecisionClassServiceImpl extends ServiceImpl<DecisionClassMapper, DecisionClass> implements IDecisionClassService {
    @Autowired
    DecisionClassMapper decisionClassMapper;

    @Override
    public List<DecisionClass> selectAll(Integer  id) {
        List<DecisionClass> list=decisionClassMapper.selectAll(id);
        return list;
    }

    @Override
    public void insertAll(DecisionClass decisionClass) {
        decisionClassMapper.insertAll(decisionClass);
    }

    @Override
    public void updateByMyId(Integer decisionClassId) {
        decisionClassMapper.updateByMyId(decisionClassId);
    }

    @Override
    public DecisionClass selectMyNeed(Integer decisionClassId) {
        return decisionClassMapper.selectMyNeed(decisionClassId);
    }

    @Override
    public void updateAll(DecisionClass decisionClass) {
        decisionClassMapper.updateAll(decisionClass);
    }
}
