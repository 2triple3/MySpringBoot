package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.ProductReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品报告 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
//@Mapper
public interface ProductReportMapper extends BaseMapper<ProductReport> {
    void updateByMyId(Integer id);

    List<ProductReport> selectAll(ProductReport productReport);

    void saveAll(ProductReport productReport);

    List<ProductReport> selectAllNeed(ProductReport productReport);

    void updateAll(ProductReport productReport);


    List<ProductReport> getProductReport(Integer productId);

    ProductReport getOneProductReport(Integer productReportId);
}
