package com.example.PaymentService.service;

import com.example.PaymentService.dto.response.EnrollmentResponse;
import com.example.PaymentService.dto.response.MessageDTO;
import com.example.PaymentService.repository.EnrollmentDtoRepository;
import com.example.PaymentService.repository.MessageDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResendEnrollmentHandler {
    @Autowired
    private EnrollmentDtoRepository enrollmentDtoRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Scheduled(fixedRate = 6000)
    private void resend(){
        List<EnrollmentResponse> events = enrollmentDtoRepository.findAll();
        if(!events.isEmpty()){
            for (EnrollmentResponse event : events){
                kafkaTemplate.send("enrollment",event).whenComplete((res,ex)->{
                    if(ex == null){
                       enrollmentDtoRepository.delete(event);
                        System.out.println("Resend enrollment success, event id: "+ event.getId());
                    }
                    else {
                        System.out.println("Resend enrollment fail, event id: "+ event.getId());
                    }
                });
            }
        }
        else{
            System.out.println("no error enrollment events");
        }
    }
}
