package com.mustafa.Producer.controller;

import com.mustafa.Producer.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/apiProducer")
public class ProducerController {

    ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/createExchangeAndQueue")
    @ApiOperation("direct-exchange ve Queue(1-2-3)'leri yayına geçirmek için çalıştırın")
    public void createExchangeAndQueue() throws IOException, TimeoutException {
        producerService.createExchangeAndQueue();
    }

    @PostMapping("/producer")
    @ApiOperation("Start the producers(1-2-3) and send the messages(1-2-3)[bind queue(1-2-3) to directExchange with routing_keys(1-2-3)]")
    public void producer() throws IOException, TimeoutException {
        producerService.publish();
    }

    @PostMapping("/producer2")
    @ApiOperation("Bir mesaj yazın, queue'yi ve Routing_Key'inizi belirtin. İsteği gönderdiğinizde yayına geçecektir.")
    public void producer2(@RequestParam String message, @RequestParam String routing_key, @RequestParam String queue) throws IOException, TimeoutException {
        producerService.publish(message, routing_key);
        producerService.createExchangeAndQueueUser(queue, routing_key);
    }




}
