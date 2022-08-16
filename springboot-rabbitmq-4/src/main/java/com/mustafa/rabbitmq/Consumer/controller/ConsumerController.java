package com.mustafa.rabbitmq.Consumer.controller;

import com.mustafa.rabbitmq.Consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/apiConsumer")
public class ConsumerController {

    ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/consumers")
    public void consumers() throws IOException, TimeoutException {

        consumerService.consumerMain();

    }
}
