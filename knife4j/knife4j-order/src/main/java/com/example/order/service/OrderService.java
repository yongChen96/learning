package com.example.order.service;

import com.example.order.entity.Order;

/**
 * @ClassName: OrderService
 * @Description: 订单服务类
 * @Author: yongchen
 * @Date: 2020/10/22 10:35
 **/
public interface OrderService {
    void create(Order order);

    Order getOrder(Long id);

    void update(Order order);

    void delete(Long id);
}
