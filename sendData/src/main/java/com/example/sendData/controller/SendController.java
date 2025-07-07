package com.example.sendData.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @GetMapping("/send")
    public String sendData() {
        return "sendData";
    }
}
