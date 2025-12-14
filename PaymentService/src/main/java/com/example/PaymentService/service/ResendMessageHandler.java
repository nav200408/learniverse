package com.example.PaymentService.service;

import com.example.PaymentService.dto.response.MessageDTO;
import com.example.PaymentService.repository.MessageDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResendMessageHandler {
    @Autowired
    private MessageDtoRepository messageDtoRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Scheduled(fixedRate = 5000)
    private void resend(){
        List<MessageDTO> events = messageDtoRepository.findAll();
        if(!events.isEmpty()){
            for (MessageDTO event : events){
                kafkaTemplate.send("notification",event).whenComplete((res,ex)->{
                    if(ex == null){
                        messageDtoRepository.delete(event);
                        System.out.println("Resend notification success, event id: "+ event.getId());
                    }
                    else {
                        System.out.println("Resend notification fail, event id: "+ event.getId());
                    }
                });
            }
        }
        else{
            System.out.println("no error notification events");
        }

    }
}
