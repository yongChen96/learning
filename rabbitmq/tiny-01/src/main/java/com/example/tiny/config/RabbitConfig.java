package com.example.tiny.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitConfig
 * @Description: Rabbit配置类
 * @Author: yongchen
 * @Date: 2021/3/17 16:15
 **/
@Configuration
public class RabbitConfig {

    /**
     * 消费队列绑定交换器
     **/
    @Bean
    public DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange()).durable(true).build();
    }

    /**
     * 延迟队列绑定交换器
     **/
    @Bean
    public DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange()).durable(true).build();
    }

    /**
     * 消费队列
     **/
    @Bean
    public Queue orderQueue(){
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 延迟队列（死信队列）
     **/
    @Bean
    public Queue orderTtlQueue(){
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                //到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                //到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 将队列绑定到交换器
     **/
    @Bean
    public Binding orderBinding(DirectExchange orderDirect, Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    /**
     * 将延迟队列绑定到交换器
     **/
    @Bean
    public Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue){
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlDirect)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }
}
