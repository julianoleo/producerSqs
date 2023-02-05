package com.sqs.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerSqsMessages {

	public static void main(String[] args) {
		SpringApplication.run(ProducerSqsMessages.class, args);
	}

}
