package com.mustafa.consumer.listener;

import com.mustafa.consumer.config.MessageConfig;
import com.mustafa.consumer.dao.CustomerMessageDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MessageConfig.QUEUE3 )
    public void listener(CustomerMessageDao customerMessageDao){
        System.out.println(customerMessageDao);
    }
}
