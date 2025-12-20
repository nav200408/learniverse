package com.example.EmailService.controller;

import com.example.EmailService.dto.MessageDTO;
import com.example.EmailService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/send")
    public String sendEmail(
            @RequestBody MessageDTO messageDTO
            ) {
        emailService.sendSimpleEmail(messageDTO.getToUser(), messageDTO.getSubject(), messageDTO.getBody());
        return "Email sent successfully to " + messageDTO.getToUser();
    }
}
