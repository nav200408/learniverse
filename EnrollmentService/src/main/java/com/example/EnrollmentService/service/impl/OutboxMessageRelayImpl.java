package com.example.EnrollmentService.service.impl;

import com.example.EnrollmentService.enums.OutboxStatus;
import com.example.EnrollmentService.model.OutboxEntity;
import com.example.EnrollmentService.producer.EnrollmentProducer;
import com.example.EnrollmentService.repository.OutboxRepository;
import com.example.EnrollmentService.service.OutboxMessageRelay;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxMessageRelayImpl implements OutboxMessageRelay {
    private final OutboxRepository outboxRepository;
    private final EnrollmentProducer enrollmentProducer;

    @Autowired
    public OutboxMessageRelayImpl(OutboxRepository outboxRepository, EnrollmentProducer enrollmentProducer) {
        this.outboxRepository = outboxRepository;
        this.enrollmentProducer = enrollmentProducer;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    @Override
    public void publishMessages() {
        List<OutboxEntity> pendingMessages = outboxRepository.findByStatus(OutboxStatus.PENDING);
        for (OutboxEntity message : pendingMessages) {
            enrollmentProducer.publishEnrollmentEvent(message.getTopic(), message.getPayload());
            message.setStatus(OutboxStatus.SENT);
            outboxRepository.save(message);
        }
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    @Override
    public void cleanupProcessedMessages() {
        outboxRepository.deleteByStatus(OutboxStatus.SENT);
    }
}
