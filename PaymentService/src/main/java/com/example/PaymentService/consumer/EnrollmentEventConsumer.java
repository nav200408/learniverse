package com.example.PaymentService.consumer;

import com.example.PaymentService.constants.PaymentConstants;
import com.example.PaymentService.enums.OutboxStatus;
import com.example.PaymentService.event.EnrollmentEvent;
import com.example.PaymentService.exception.PaymentProcessingException;
import com.example.PaymentService.model.OutboxEntity;
import com.example.PaymentService.model.PaymentEntity;
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

    public EnrollmentEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public EnrollmentEventConsumer() {
    }

    @KafkaListener(topics = PaymentConstants.KAFKA_TOPIC_ENROLLMENT_SUCCESS, groupId = "payment-group")
    @Transactional
    public void enrollSuccess(EnrollmentEvent enrollmentEvent) {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentEntity paymentEntity = paymentService.paymentUpdateHandler(enrollmentEvent.getPaymentId(), "SUCCESS");
        OutboxEntity outboxEnroll = new OutboxEntity();
        outboxEnroll.setTopic(PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_SUCCESS);
        outboxEnroll.setStatus(OutboxStatus.PENDING);
        try {
            outboxEnroll.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
        } catch (JsonProcessingException e) {
            throw new PaymentProcessingException("Failed to serialize enrollment event", e);
        }
    }

    @KafkaListener(topics = PaymentConstants.KAFKA_TOPIC_ENROLLMENT_FAIL, groupId = "payment-group")
    public void enrollFail(EnrollmentEvent enrollmentEvent) {
        PaymentEntity paymentEntity = paymentService.paymentUpdateHandler(enrollmentEvent.getPaymentId(), "FAIL");
        ObjectMapper objectMapper = new ObjectMapper();
        OutboxEntity outboxEnroll = new OutboxEntity();
        outboxEnroll.setTopic(PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_FAIL);
        outboxEnroll.setStatus(OutboxStatus.PENDING);
        try {
            outboxEnroll.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
        } catch (JsonProcessingException e) {
            throw new PaymentProcessingException("Failed to serialize enrollment event", e);
        }
    }
}
