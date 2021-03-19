package com.example.tiny.config;

import lombok.Getter;

/**
 * @ClassName: QueueEnum
 * @Description: Queue枚举类
 * @Author: yongchen
 * @Date: 2021/3/17 16:12
 **/
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("ic.order.direct", "ic.order.cancel", "ic.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("ic.order.direct.ttl", "ic.order.cancel.ttl", "ic.order.cancel.ttl");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

}
