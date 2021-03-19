package com.example.tiny.provider;

import com.example.tiny.config.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CancelOrderSender
 * @Description: 生产者
 * @Author: yongchen
 * @Date: 2021/3/17 16:36
 **/
@Slf4j
@Component
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @Author: yongchen
     * @Description: 给延迟队列发送消息
     * @Date: 16:47 2021/3/17
     * @Param: [orderId, delayTimes]
     * @return: void
     **/
    public void sendTtlMessage(Long orderId, final Long delayTimes) {
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(),
                orderId,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置延迟时间
                        message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                        return message;
                    }
                });
        log.info("send delay message orderId:{}",orderId);
    }
}
