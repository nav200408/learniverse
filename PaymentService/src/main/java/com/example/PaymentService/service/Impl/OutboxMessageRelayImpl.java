package com.example.PaymentService.service.Impl;

import com.example.PaymentService.enums.OutboxStatus;
import com.example.PaymentService.model.OutboxEntity;
import com.example.PaymentService.producer.PaymentProducer;
import com.example.PaymentService.repository.OutboxRepository;
import com.example.PaymentService.service.OutboxMessageRelay;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxMessageRelayImpl implements OutboxMessageRelay {
    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private PaymentProducer paymentProducer;

    public OutboxMessageRelayImpl(OutboxRepository outboxRepository, PaymentProducer paymentProducer) {
        this.outboxRepository = outboxRepository;
        this.paymentProducer = paymentProducer;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    @Override
    public void publishMessages() {
        List<OutboxEntity> pendingMessages = outboxRepository.findByStatus(OutboxStatus.PENDING);
        for (OutboxEntity message : pendingMessages) {
            paymentProducer.publishPaymentCompletedEvent(message.getTopic(), message.getPayload());
            message.setStatus(OutboxStatus.SENT);
            outboxRepository.save(message);
        }
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void cleanupProcessedMessages() {
        outboxRepository.deleteByStatus(OutboxStatus.SENT);
    }
}
