package com.example.EmailService.service;

import com.example.EmailService.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDTO messageDTO) {
        emailService.sendSimpleEmail(messageDTO.getTo(), messageDTO.getSubject(), messageDTO.getBody());
    }
}
