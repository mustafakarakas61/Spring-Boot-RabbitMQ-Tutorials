package com.mustafa.Consumer.controller;

import com.mustafa.Consumer.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/receiver")
    @ApiOperation("Start the consumers(1-2-3)")
    public void receiver() throws IOException, TimeoutException {
        consumerService.receive();
    }



}
