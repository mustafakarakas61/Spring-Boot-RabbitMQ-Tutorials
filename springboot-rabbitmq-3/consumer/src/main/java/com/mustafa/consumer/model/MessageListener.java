package com.mustafa.consumer.model;

import com.mustafa.consumer.config.MessageConfig;
import com.mustafa.consumer.dto.CustomerMessageDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MessageConfig.QUEUE1)
    public void listenerDirect(CustomerMessageDao customerMessageDao){
        System.out.println( customerMessageDao.getMessage());
    }

    @RabbitListener(queues = MessageConfig.QUEUE2)
    public void listenerFanout(CustomerMessageDao customerMessageDao){
        System.out.println(customerMessageDao.getMessage());
    }


    @RabbitListener(queues = MessageConfig.QUEUE3 )
    public void listenerTopic(CustomerMessageDao customerMessageDao){
        System.out.println( customerMessageDao.getMessage());
    }
}
