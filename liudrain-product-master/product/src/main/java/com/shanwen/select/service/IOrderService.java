package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 付款订单 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IOrderService extends IService<Order> {
    int createOrder(Order order);


    void updateOrder(Order order);

    Order selectOrder(String orderNum);

    //获取订单的数据信息
    Map getOrderData(Map map);


}
