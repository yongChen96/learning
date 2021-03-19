package com.example.tiny.consumer;

import com.example.tiny.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: CancelOrderReceiver
 * @Description: rabbitMQ消费者
 * @Author: yongchen
 * @Date: 2021/3/19 17:05
 **/
@Slf4j
@Component
@RabbitListener(queues = "ic.order.cancel")
public class CancelOrderReceiver {

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void handle(Long orderId){
        log.info("receive delay message orderId:{}",orderId);
        orderService.cancelOrder(orderId);
    }
}
