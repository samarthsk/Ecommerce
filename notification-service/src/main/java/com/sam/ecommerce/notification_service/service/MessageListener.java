package com.sam.ecommerce.notification_service.service;

import com.sam.ecommerce.notification_service.dto.OrderDTO;
import com.sam.ecommerce.notification_service.entity.Notification;
import com.sam.ecommerce.notification_service.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private NotificationRepository notificationRepository;

//    @RabbitListener(queues = "order_notifications")
//    public void receiveOrderNotification(String message) {
//        System.out.println("Received notification: " + message);
//    }

    @RabbitListener(queues = "order_notifications")
    public void receiveOrderNotification(OrderDTO orderDTO) {
        System.out.println("Received order: " + orderDTO);

        // Save notification to database
        Notification notification = new Notification();
        notification.setUserId(orderDTO.getUserId());
        notification.setMessage("Order placed! Order ID: " + orderDTO.getId() +
                ", Total: $" + orderDTO.getTotalPrice());
        notification.setType("ORDER_CREATED");
        notification.setSent(true);

        notificationRepository.save(notification);

        System.out.println("Notification saved to database!");
    }
}
