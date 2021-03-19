package com.example.tiny.service;

/**
 * @ClassName: OrderService
 * @Description: 订单服务
 * @Author: yongchen
 * @Date: 2021/3/19 17:24
 **/
public interface OrderService {

    /**
     * @Author: yongchen
     * @Description: 取消订单
     * @Date: 17:29 2021/3/19
     * @Param: [orderId]
     * @return: void
     **/
    void cancelOrder(Long orderId);
}
