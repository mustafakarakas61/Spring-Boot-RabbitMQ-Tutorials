package com.mustafa.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    //-------------------------------------------------------------------Queues Variables
    public static final String QUEUE1 = "mustafa_queue1";
    public static final String QUEUE2 = "mustafa_queue2";
    public static final String QUEUE3 = "mustafa_queue3";

    //-------------------------------------------------------------------Exchanges Variables
    public static final String DIRECTEXCHANGE = "mustafa.direct";
    public static final String FANOUTEXCHANGE = "mustafa.fanout";
    public static final String TOPICEXCHANGE = "mustafa.topic";
    public static final String ROUTINGKEY_DIRECT = "routingKeyDirect";
    public static final String ROUTINGKEY_TOPIC = "routingKeyTopic";

    //-------------------------------------------------------------------Methods
    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public Queue queue3() {
        return new Queue(QUEUE3);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECTEXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUTEXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPICEXCHANGE);
    }

    //-------------------------------------------------------------------Binding Methods
    @Bean
    public Binding bindingDirect(Queue queue1, DirectExchange directExchange) {
        return BindingBuilder.bind(queue1).to(directExchange).with(ROUTINGKEY_DIRECT);
    }

    @Bean
    public Binding bindingFanout(Queue queue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    @Bean
    public Binding bindingTopic(Queue queue3, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue3).to(topicExchange).with(ROUTINGKEY_TOPIC);
    }

    //-------------------------------------------------------------------Message Converter
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //-------------------------------------------------------------------Template
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}