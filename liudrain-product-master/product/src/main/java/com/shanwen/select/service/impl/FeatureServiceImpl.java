package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Feature;
import com.shanwen.select.mapper.FeatureMapper;
import com.shanwen.select.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeatureServiceImpl extends ServiceImpl<FeatureMapper, Feature> implements FeatureService {
    @Autowired
    FeatureMapper mapper;

    @Override
    public List<Feature> getFeature(Feature feature) {
        return mapper.getFeature(feature);
    }

    @Override
    public List<Feature> getFeatureList(Map map) {
        return mapper.getFeatureList(map);
    }
}
