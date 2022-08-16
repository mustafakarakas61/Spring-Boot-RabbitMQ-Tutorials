package com.mustafa.rabbitmq.Publisher.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class PublisherService {
    public void publisherMain(String queue, String message) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        channel.basicPublish("", queue, false,false,null, message.getBytes());


        channel.close();
        connection.close();
    }


}
