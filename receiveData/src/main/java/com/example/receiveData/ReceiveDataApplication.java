package com.example.receiveData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReceiveDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiveDataApplication.class, args);
	}

}
