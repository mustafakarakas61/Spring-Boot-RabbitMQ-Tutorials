package com.mustafa.Producer.controller;

import com.mustafa.Producer.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/createExchangeAndQueue")
    @ApiOperation("Exchange ve Queue'leri yayına geçirmek için çalıştırın")
    public void createExchangeAndQueue() throws IOException, TimeoutException {
        producerService.createExchangeAndQueue();
    }

    @GetMapping("/producer")
    @ApiOperation("Start the producers(1-2-3)")
    public void producer() throws IOException, TimeoutException {
        producerService.publish();
    }



}
