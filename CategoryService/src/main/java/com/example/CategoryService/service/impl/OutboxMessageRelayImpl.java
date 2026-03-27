package com.example.CategoryService.service.impl;

import com.example.CategoryService.enums.OutboxStatus;
import com.example.CategoryService.model.OutboxEntity;
import com.example.CategoryService.producer.CategoryProducer;
import com.example.CategoryService.repository.OutboxRepository;
import com.example.CategoryService.service.OutboxMessageRelay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutboxMessageRelayImpl implements OutboxMessageRelay {
    private final OutboxRepository outboxRepository;
    private final CategoryProducer categoryProducer;

    @Autowired
    public OutboxMessageRelayImpl(OutboxRepository outboxRepository, CategoryProducer categoryProducer) {
        this.outboxRepository = outboxRepository;
        this.categoryProducer = categoryProducer;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    @Override
    public void publishMessages() {
        List<OutboxEntity> pendingMessages = outboxRepository.findByStatus(OutboxStatus.PENDING);
        for (OutboxEntity message : pendingMessages) {
            try {
                categoryProducer.sendMessage(message.getTopic(), message.getPayload());
                message.setStatus(OutboxStatus.SENT);
            } catch (Exception e) {
                message.setStatus(OutboxStatus.FAILED);
            }
            outboxRepository.save(message);
        }
    }
}
