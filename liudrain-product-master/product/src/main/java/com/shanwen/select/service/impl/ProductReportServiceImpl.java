package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.ProductReport;
import com.shanwen.select.mapper.ProductReportMapper;
import com.shanwen.select.service.IProductReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品报告 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class ProductReportServiceImpl extends ServiceImpl<ProductReportMapper, ProductReport>
        implements IProductReportService {
    @Autowired
    ProductReportMapper productReportMapper;

    @Override
    public void updateByMyId(Integer productReportId) {
        productReportMapper.updateByMyId(productReportId);
    }

    @Override
    public List<ProductReport> selectAll(ProductReport productReport) {
        List<ProductReport> list = productReportMapper.selectAll(productReport);
        return list;
    }

    @Override
    public void saveAll(ProductReport productReport) {
        productReportMapper.saveAll(productReport);
    }

    @Override
    public List<ProductReport> selectAllNeed(ProductReport productReport) {
        List<ProductReport> list = productReportMapper.selectAllNeed(productReport);
        return list;
    }

    @Override
    public void updateAll(ProductReport productReport) {
        productReportMapper.updateAll(productReport);
    }

    @Override
    public List<ProductReport> getProductReport(Integer productId) {
        return productReportMapper.getProductReport(productId);
    }

    @Override
    public ProductReport getOneProductReport(Integer productReportId) {
        return productReportMapper.getOneProductReport(productReportId);

    }
}
