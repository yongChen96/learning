package com.example.tiny.controller;

import com.example.tiny.provider.CancelOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ProviderController
 * @Description: 生产者控制器
 * @Author: yongchen
 * @Date: 2021/3/17 16:54
 **/
@RestController
@RequestMapping("/provider")
public class OrderController {

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @RequestMapping(value = "/sendMsg")
    public Long sendMsg(@RequestParam("orderId") Long orderId){
        cancelOrderSender.sendTtlMessage(orderId, 30000L);
        return orderId;
    }
}
