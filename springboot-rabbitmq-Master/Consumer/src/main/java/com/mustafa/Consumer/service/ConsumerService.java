package com.mustafa.Consumer.service;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService {

    //-----------------------------------------------------------Variables
    /*
    todo : Burada Geliştirme Yapılacak
     */
    public static String QUEUE_NAME_1 = "direct-queue-1";
    public static String QUEUE_NAME_2 = "direct-queue-2";
    public static String QUEUE_NAME_3 = "direct-queue-3";

    //-----------------------------------------------------------Methods
    public void receive() throws IOException, TimeoutException {

        Connection connection = ConsumerService.getConnection();

        if (connection != null) {
            Channel channel = connection.createChannel();

            //Consumer reading from queue 1
            Consumer consumer1 = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Message Received Queue 1 '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME_1, true, consumer1);

            //Consumer reading from queue 2
            Consumer consumer2 = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Message Received Queue 2 '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME_2, true, consumer2);

            //Consumer reading from queue 3
            Consumer consumer3 = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Message Received Queue 3 '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME_3, true, consumer3);
            channel.close();
            connection.close();

        }

    }

    //-----------------------------------------------------------Connection Method
    public static Connection getConnection() throws IOException, TimeoutException {

        Connection connection;
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setUsername("");
        //factory.setPassword("");
        //factory.setVirtualHost("");
        factory.setHost("localhost");
        //factory.setPort(15672);
        //Bu değerleri default çekiyor

        connection = factory.newConnection();
        return connection;

    }
}
