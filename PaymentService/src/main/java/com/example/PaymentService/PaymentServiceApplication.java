package com.example.PaymentService;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
@Bean
	NewTopic notification(){
		return new NewTopic("notification",1,(short) 1);
	}
@Bean
	NewTopic enrollment(){
		return new NewTopic("enrollment",1,(short) 1);
	}

}
