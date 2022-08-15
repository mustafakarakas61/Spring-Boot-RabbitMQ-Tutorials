package com.mustafa.consumer.listener;

import com.mustafa.consumer.config.MessageConfig;
import com.mustafa.consumer.dao.CustomerMessageDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
