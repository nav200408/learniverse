package com.example.CourseService.service.Impl;

import com.example.CourseService.enums.OutboxStatus;
import com.example.CourseService.model.OutboxEntity;
import com.example.CourseService.producer.CourseProducer;
import com.example.CourseService.repository.OutboxRepository;
import com.example.CourseService.service.OutboxMessageRelay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutboxMessageRelayImpl implements OutboxMessageRelay {
    private final OutboxRepository outboxRepository;
    private final CourseProducer courseProducer;

    @Autowired
    public OutboxMessageRelayImpl(OutboxRepository outboxRepository, CourseProducer courseProducer) {
        this.outboxRepository = outboxRepository;
        this.courseProducer = courseProducer;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    @Override
    public void publishMessages() {
        List<OutboxEntity> pendingMessages = outboxRepository.findByStatus(OutboxStatus.PENDING);
        for (OutboxEntity message : pendingMessages) {
            try {
                courseProducer.sendMessage(message.getTopic(), message.getPayload());
                message.setStatus(OutboxStatus.SENT);
            } catch (Exception e) {
                message.setStatus(OutboxStatus.FAILED);
            }
            outboxRepository.save(message);
        }
    }
}
