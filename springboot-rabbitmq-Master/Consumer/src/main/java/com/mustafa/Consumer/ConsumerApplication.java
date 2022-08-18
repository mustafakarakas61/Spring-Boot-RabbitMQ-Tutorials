package com.mustafa.Consumer;

import com.mustafa.Consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class ConsumerApplication {

	static ConsumerService consumerService;

	@Autowired
	public ConsumerApplication(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(ConsumerApplication.class, args);

		consumerService.receiveConsumer();
	}

}
