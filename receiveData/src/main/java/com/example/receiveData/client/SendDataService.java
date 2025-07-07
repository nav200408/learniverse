package com.example.receiveData.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sendData", fallback = SendDataServiceFallback.class)
public interface SendDataService {
    @GetMapping("/send")
    public String sendData();
}

@Component
class SendDataServiceFallback implements SendDataService{

    @Override
    public String sendData() {
        return "sendData function error";
    }
}

