package com.example.EnrollmentService.model;

import com.example.EnrollmentService.enums.OutboxStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "outbox_enrollment")
public class OutboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;
    @Column(columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public OutboxEntity() {
    }

    public OutboxEntity(String payload, String topic, int id, OutboxStatus status, LocalDateTime localDateTime) {
        this.payload = payload;
        this.topic = topic;
        this.id = id;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public void setStatus(OutboxStatus status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
