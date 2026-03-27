package com.example.PaymentService.consumer;

import com.example.PaymentService.constants.PaymentConstants;
import com.example.PaymentService.enums.OutboxStatus;
import com.example.PaymentService.event.EnrollmentEvent;
import com.example.PaymentService.exception.PaymentProcessingException;
import com.example.PaymentService.model.OutboxEntity;
import com.example.PaymentService.model.PaymentEntity;
import com.example.PaymentService.repository.OutboxRepository;
import com.example.PaymentService.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentEventConsumer {
    @Autowired
    PaymentService paymentService;
    @Autowired
    OutboxRepository outboxRepository;

    public EnrollmentEventConsumer(PaymentService paymentService, OutboxRepository outboxRepository) {
        this.paymentService = paymentService;
        this.outboxRepository = outboxRepository;
    }

    public EnrollmentEventConsumer() {
    }

    @KafkaListener(topics = PaymentConstants.KAFKA_TOPIC_ENROLLMENT_SUCCESS, groupId = "payment-group")
    @Transactional
    public void enrollSuccess(EnrollmentEvent enrollmentEvent) {
        System.out.println("hello");
        System.out.println(enrollmentEvent.getPaymentId());
        ObjectMapper objectMapper = new ObjectMapper();
        paymentService.paymentUpdateHandler(enrollmentEvent.getPaymentId(), "SUCCESS");
        OutboxEntity outboxEnroll = new OutboxEntity();
        outboxEnroll.setTopic(PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_SUCCESS);
        outboxEnroll.setStatus(OutboxStatus.PENDING);
        try {
            outboxEnroll.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
        } catch (JsonProcessingException e) {
            throw new PaymentProcessingException("Failed to serialize enrollment event", e);
        }
        outboxRepository.save(outboxEnroll);
    }

    @KafkaListener(topics = PaymentConstants.KAFKA_TOPIC_ENROLLMENT_FAIL, groupId = "payment-group")
    @Transactional
    public void enrollFail(EnrollmentEvent enrollmentEvent) {
        System.out.println(enrollmentEvent.getPaymentId());
        paymentService.paymentUpdateHandler(enrollmentEvent.getPaymentId(), "FAIL");
        ObjectMapper objectMapper = new ObjectMapper();
        OutboxEntity outboxEnroll = new OutboxEntity();
        outboxEnroll.setTopic(PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_FAIL);
        outboxEnroll.setStatus(OutboxStatus.PENDING);
        try {
            outboxEnroll.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
        } catch (JsonProcessingException e) {
            throw new PaymentProcessingException("Failed to serialize enrollment event", e);
        }
        outboxRepository.save(outboxEnroll);
    }
}
