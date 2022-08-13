package com.mustafa;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEQUE)
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
