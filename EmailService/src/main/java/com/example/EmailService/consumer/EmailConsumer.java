package com.example.EmailService.consumer;

import com.example.EmailService.constants.EmailConstant;
import com.example.EmailService.event.EnrollmentEvent;
import com.example.EmailService.service.Impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @KafkaListener(topics = EmailConstant.KAFKA_TOPIC_PAYMENT_PROCESSING_SUCCESS,groupId = "notification")
    public void listenSuccessEnrollment(EnrollmentEvent enrollmentEvent) {
        emailServiceImpl.sendSimpleEmail(enrollmentEvent.getEmail(), "YOU SUCCESSFULLY PURCHASE A NEW COURSE !!!",
                "You just enroll in the course " + enrollmentEvent.getCourseId());
    }

    @KafkaListener(topics = EmailConstant.KAFKA_TOPIC_PAYMENT_PROCESSING_FAIL, groupId = "notification")
    public void listenFailEnrollment(EnrollmentEvent enrollmentEvent) {
        emailServiceImpl.sendSimpleEmail(enrollmentEvent.getEmail(), "YOU FAILED TO PURCHASE A NEW COURSE !!!",
                "You just fail to enroll in the course " + enrollmentEvent.getCourseId());
    }

}
