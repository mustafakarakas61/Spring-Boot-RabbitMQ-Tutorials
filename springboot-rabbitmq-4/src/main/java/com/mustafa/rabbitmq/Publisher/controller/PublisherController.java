package com.mustafa.rabbitmq.Publisher.controller;

import com.mustafa.rabbitmq.Publisher.service.PublisherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/apiPublish")
public class PublisherController {
    PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/queues")
    @ApiOperation("RabbitMQ'ye belirlediğiniz kuyruğa gidecek gönderecek")
    public String queues(@RequestParam String queue, @RequestParam String message) throws IOException, TimeoutException {

        publisherService.publisherMain(queue, message);

        return "Success";
    }

}
