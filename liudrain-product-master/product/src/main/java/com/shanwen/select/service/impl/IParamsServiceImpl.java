package com.shanwen.select.service.impl;

import com.shanwen.select.entity.Params;
import com.shanwen.select.mapper.ParamsMapper;
import com.shanwen.select.service.IParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IParamsServiceImpl implements IParamsService {
    @Autowired
    ParamsMapper mapper;

    @Override
    public List<Params> selectParams() {
        return mapper.selectParams();
    }

    @Override
    public List<Params> selectParams2() {
        return mapper.selectParams2();
    }
}
