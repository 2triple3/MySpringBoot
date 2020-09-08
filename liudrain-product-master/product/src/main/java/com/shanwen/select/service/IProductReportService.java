package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.ProductReport;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 产品报告 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IProductReportService extends IService<ProductReport> {
    void updateByMyId(Integer productReportId);

    List<ProductReport> selectAll(ProductReport productReport);

    void saveAll(ProductReport productReport);

    List<ProductReport> selectAllNeed(ProductReport productReport);

    void updateAll(ProductReport productReport);


    List<ProductReport> getProductReport(Integer productId);

    ProductReport getOneProductReport(Integer productReportId);

}
