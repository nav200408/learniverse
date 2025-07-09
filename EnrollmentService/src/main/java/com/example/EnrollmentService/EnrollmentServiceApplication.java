package com.example.EnrollmentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@SpringBootApplication
public class EnrollmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentServiceApplication.class, args);
	}
	@Bean
	JsonMessageConverter converter(){
		return new JsonMessageConverter();
	}
}
