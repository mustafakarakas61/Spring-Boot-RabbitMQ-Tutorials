package com.mustafa.publisher.controller;

import com.mustafa.publisher.config.MessageConfig;
import com.mustafa.publisher.dto.CustomerMessageDao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class PublisherController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/publishDirect")
    public String publishMessageDirect(@RequestBody CustomerMessageDao customerMessageDao){
        customerMessageDao.setMessageId(UUID.randomUUID().toString());
        customerMessageDao.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(MessageConfig.DIRECTEXCHANGE, MessageConfig.ROUTINGKEY_DIRECT, customerMessageDao);
        return "Mesaj Yayınlandı : DirectExchange";
    }

    @PostMapping("/publishFanout")
    public String publishMessageFanout(@RequestBody CustomerMessageDao customerMessageDao){
        customerMessageDao.setMessageId(UUID.randomUUID().toString());
        customerMessageDao.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(MessageConfig.FANOUTEXCHANGE, null, customerMessageDao);
        return "Mesaj Yayınlandı : FanoutExchange";
    }

    @PostMapping("/publishTopic")
    public String publishMessageTopic(@RequestBody CustomerMessageDao customerMessageDao){
        customerMessageDao.setMessageId(UUID.randomUUID().toString());
        customerMessageDao.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(MessageConfig.TOPICEXCHANGE,MessageConfig.ROUTINGKEY_TOPIC, customerMessageDao);
        return "Mesaj Yayınlandı : TopicExchange";
    }
}
