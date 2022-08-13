package com.mustafa.consumer;

import com.mustafa.config.MessagingConfig;
import com.mustafa.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Kuyruktan mesaj ulaştı : "+orderStatus);
    }
}
