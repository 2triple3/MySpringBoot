package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Feature;

import java.util.List;
import java.util.Map;


public interface FeatureMapper extends BaseMapper<Feature> {

    List<Feature> getFeature(Feature object);

    List<Feature> getFeatureList(Map map);
}
