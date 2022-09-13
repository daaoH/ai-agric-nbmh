package com.hszn.nbmh.order.rabbitmq;

import cn.hutool.core.util.IdUtil;
import com.hszn.nbmh.order.api.constant.OrderQueueEnum;
import com.hszn.nbmh.order.api.params.input.CreateOrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author：袁德民
 * @Description: 订单发送队列
 * @Date:下午8:43 22/9/13
 * @Modified By:
 */
@Slf4j
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订单创建
     *
     * @param orderMessage
     * @return void
     */
    public void sendCreateOrderMessage(CreateOrderMessage orderMessage) {
        log.info("sendCreateOrderMessage:" + orderMessage.toString());
        convertAndSend(OrderQueueEnum.QUEUE_ORDER_CREATE, orderMessage);
    }


    private void convertAndSend(OrderQueueEnum queue, Object message) {
        CorrelationData correlationData = new CorrelationData(IdUtil.fastSimpleUUID());
        rabbitTemplate.convertAndSend(queue.getExchange(), queue.getRouteKey(), message, correlationData);
    }

    private void convertAndSend(OrderQueueEnum queue, Object message, MessagePostProcessor messagePostProcessor) {
        CorrelationData correlationData = new CorrelationData(IdUtil.fastSimpleUUID());
        rabbitTemplate.convertAndSend(queue.getExchange(), queue.getRouteKey(), message, messagePostProcessor,
                correlationData);
    }
}
