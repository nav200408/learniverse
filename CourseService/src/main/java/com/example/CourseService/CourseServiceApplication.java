package com.example.CourseService;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}
	@Bean
	NewTopic category(){
		return new NewTopic("category",1,(short) 1);
	}
}
