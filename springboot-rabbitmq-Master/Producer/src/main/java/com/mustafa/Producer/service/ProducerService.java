package com.mustafa.Producer.service;

import com.mustafa.Producer.model.enums.ExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerService {
    //---------------------------------------------------------------------Variables
    private Connection connection;
    private Channel channel;

    public static String EXCHANGE_NAME_DIRECT = "direct-exchange";

    private final static String MESSAGE_1 = "First Direct Message Example";
    private final static String MESSAGE_2 = "Second Direct Message Example";
    private final static String MESSAGE_3 = "Third Direct Message Example";

    public static String QUEUE_NAME_1 = "direct-queue-1";
    public static String QUEUE_NAME_2 = "direct-queue-2";
    public static String QUEUE_NAME_3 = "direct-queue-3";

    public static String ROUTING_KEY_1 = "direct-key-1";
    public static String ROUTING_KEY_2 = "direct-key-2";
    public static String ROUTING_KEY_3 = "direct-key-3";


    //-------------------------------------------------------createEXCHANGEandQUEUE and PUBLISH METHOD
    public void createExchangeAndQueue() throws IOException, TimeoutException {

        connection = ProducerService.getConnection();

        if (connection != null) {
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, ExchangeType.DIRECT.getExchangeName(), true);

            //First Queue
            channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME_DIRECT, ROUTING_KEY_1);

            //Second Queue
            channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME_DIRECT, ROUTING_KEY_2);

            //Third Queue
            channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);
            channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME_DIRECT, ROUTING_KEY_3);

            channel.close();
            connection.close();

        }

    }

    /**
     * “MESSAGE_1” with routing key as “direct-key-1“.
     * “MESSAGE_2” with routing key as “direct-key-2“.
     * “MESSAGE_3” with routing key as “direct-key-3“.
     */
    public void publish() throws IOException, TimeoutException {
        connection = ProducerService.getConnection();

        if (connection != null) {
            channel = connection.createChannel();

            //First message sent by using ROUTING_KEY_1
            channel.basicPublish(ProducerService.EXCHANGE_NAME_DIRECT, ProducerService.ROUTING_KEY_1, null, MESSAGE_1.getBytes());
            System.out.println("Message Sent '" + MESSAGE_1 + "'");

            //Second message sent by using ROUTING_KEY_2
            channel.basicPublish(ProducerService.EXCHANGE_NAME_DIRECT, ProducerService.ROUTING_KEY_2, null, MESSAGE_2.getBytes());
            System.out.println("Message Sent '" + MESSAGE_2 + "'");

            //Third message sent by using ROUTING_KEY_3
            channel.basicPublish(ProducerService.EXCHANGE_NAME_DIRECT, ProducerService.ROUTING_KEY_3, null, MESSAGE_3.getBytes());
            System.out.println("Message Sent '" + MESSAGE_3 + "'");
            channel.close();
            connection.close();

        }

    }


    //-------------------------------------------------------createEXCHANGEandQUEUE and PUBLISH METHOD for ■USER■
    public void createExchangeAndQueueUser(String queue, String routing_key) throws IOException, TimeoutException {

        connection = ProducerService.getConnection();

        if (connection != null) {
            channel = connection.createChannel();
            //ExchangeType
            channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, ExchangeType.DIRECT.getExchangeName(), true);

            //Create user's queue with user's routing_key
            channel.queueDeclare(queue, true, false, false, null);
            channel.queueBind(queue, EXCHANGE_NAME_DIRECT, routing_key);

            channel.close();
            connection.close();

        }

    }

    public void publish(String message, String routing_key) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();

        if (connection != null) {
            channel = connection.createChannel();

            //User's message sent by using user's routing_key
            channel.basicPublish(ProducerService.EXCHANGE_NAME_DIRECT, routing_key, null, message.getBytes());
            System.out.println("Message Sent '" + message + "'");

            channel.close();
            connection.close();

        }

    }

    /*
    todo : Var olan queue'leri ve routing_key'leri yakalayıp diziye aktarabiliyor musun? Eğer yapılabiliyorsa kullanıcı bunlar arasından swagger üzerinden seçsin. Bu işlem kullanıcıya kolaylık sağlayacaktır.
    todo : Kullanıcı EXCHANGE tipini seçebilsin. Direct-Fanout-Topic-Headers. Belki bunu swagger üzerinden 1-2-3-4 gibi id numaralandırmasıyla yapılabilir.
     */
    //---------------------------------------------------------------------Connection Config
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

    public void sendMessage(String message,String queueName, String routing_KeyName, String exchangeName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if(connection != null) {
            channel.queueBind(queueName, exchangeName, routing_KeyName);
            channel.basicPublish(exchangeName,routing_KeyName,null,message.getBytes());
            channel.close();
            connection.close();
        }
    }


    //---------------------------------------------------------------------Create Queue
    public void createQueue(String queueName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if (connection != null) {
            channel.queueDeclare(queueName, true, false, false, null);
            channel.close();
            connection.close();
        }
    }

    //---------------------------------------------------------------------Create Exchange
    public void createExchangeDirect(String exchangeName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if (connection != null) {
            channel.exchangeDeclare(exchangeName, ExchangeType.DIRECT.getExchangeName(), true);
            channel.close();
            connection.close();
        }
    }

    public void createExchangeFanout(String exchangeName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if (connection != null) {
            channel.exchangeDeclare(exchangeName, ExchangeType.FANOUT.getExchangeName(), true);
            channel.close();
            connection.close();
        }
    }

    public void createExchangeHeader(String exchangeName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if (connection != null) {
            channel.exchangeDeclare(exchangeName, ExchangeType.HEADER.getExchangeName(), true);
            channel.close();
            connection.close();
        }
    }

    public void createExchangeTopic(String exchangeName) throws IOException, TimeoutException {
        connection = ProducerService.getConnection();
        channel = connection.createChannel();

        if (connection != null) {
            channel.exchangeDeclare(exchangeName, ExchangeType.TOPIC.getExchangeName(), true);
            channel.close();
            connection.close();
        }
    }

}
