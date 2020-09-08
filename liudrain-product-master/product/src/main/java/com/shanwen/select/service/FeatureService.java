package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Feature;

import java.util.List;
import java.util.Map;

public interface FeatureService extends IService<Feature> {
    List<Feature> getFeature(Feature feature);


    List<Feature> getFeatureList(Map map);
}
