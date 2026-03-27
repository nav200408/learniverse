package com.example.EmailService.controller;

import com.example.EmailService.event.MessageDTO;
import com.example.EmailService.service.Impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @PostMapping("/send")
    public String sendEmail(
            @RequestBody MessageDTO messageDTO
            ) {
        emailServiceImpl.sendSimpleEmail(messageDTO.getToUser(), messageDTO.getSubject(), messageDTO.getBody());
        return "Email sent successfully to " + messageDTO.getToUser();
    }
}
