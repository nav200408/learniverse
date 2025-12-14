package com.example.CourseService.service;

import com.example.CourseService.dto.CategoryDto;
import com.example.CourseService.repository.CategoryKafkaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResendEventHandler {
    @Autowired
    private CategoryKafkaEvent categoryKafkaEvent;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Scheduled(fixedRate = 2000)
    private void resend(){
        List<CategoryDto> events = categoryKafkaEvent.findAll();
        if(!events.isEmpty()){
            for (CategoryDto event : events){
            kafkaTemplate.send("category",event).whenComplete((res,ex)->{
                if(ex == null){
                    categoryKafkaEvent.delete(event);
                    System.out.println("Resend success, event id: "+ event.getId());
                }
                else {
                    System.out.println("Resend fail, event id: "+ event.getId());
                }
            });
            }
        }
        else{
            System.out.println("no error events");
        }

    }
}
