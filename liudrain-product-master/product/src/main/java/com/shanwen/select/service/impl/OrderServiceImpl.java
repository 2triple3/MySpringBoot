package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Order;
import com.shanwen.select.mapper.OrderMapper;
import com.shanwen.select.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 付款订单 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Autowired
    OrderMapper orderMapper;

    @Override
    public int createOrder(Order order) {
        return orderMapper.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public Order selectOrder(String orderNum) {
        return orderMapper.selectOrder(orderNum);
    }

    @Override
    public Map getOrderData(Map map) {
        return orderMapper.getOrderData(map);
    }
}
