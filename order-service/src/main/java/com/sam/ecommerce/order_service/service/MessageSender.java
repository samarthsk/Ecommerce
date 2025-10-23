package com.sam.ecommerce.order_service.service;

import com.sam.ecommerce.order_service.dto.OrderDTO;
import com.sam.ecommerce.order_service.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.support.converter.MessageConverter;

@Service
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

//    public void sendOrderNotification(String message) {
//        rabbitTemplate.convertAndSend("order_notifications", message);
//    }

    public void sendOrderNotification(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setStatus(order.getStatus());

        rabbitTemplate.convertAndSend("order_notifications", orderDTO);
    }

}
