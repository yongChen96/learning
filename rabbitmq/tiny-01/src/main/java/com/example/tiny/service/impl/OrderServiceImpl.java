package com.example.tiny.service.impl;

import com.example.tiny.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Description: 订单服务
 * @Author: yongchen
 * @Date: 2021/3/19 17:24
 **/
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * @Author: yongchen
     * @Description: 取消订单
     * @Date: 17:29 2021/3/19
     * @Param: [orderId]
     * @return: void
     **/
    @Override
    public void cancelOrder(Long orderId) {
        log.info("receive delay message orderId:{}",orderId);
    }
}
