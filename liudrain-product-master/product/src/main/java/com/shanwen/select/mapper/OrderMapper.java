package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.Order;

import java.util.Map;

/**
 * <p>
 * 付款订单 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface OrderMapper extends BaseMapper<Order> {
    int createOrder(Order order);


    int updateOrder(Order order);

    Order selectOrder(String orderNum);

    //获取订单的数据信息
    Map getOrderData(Map map);
}
