package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.ProductFeature;
import com.shanwen.select.mapper.ProductFeatureMapper;
import com.shanwen.select.service.ProductFeatureService;
import org.springframework.stereotype.Service;

@Service
public class ProductFeatureServiceImpl extends ServiceImpl<ProductFeatureMapper, ProductFeature> implements ProductFeatureService {

}
