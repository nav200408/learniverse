package com.example.PaymentService.dto.response;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class MessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String toUser;
    private String subject;
    private String body;

    public MessageDTO() {
    }

    public MessageDTO(String to, String subject, String body) {
        this.toUser = to;
        this.subject = subject;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String to) {
        this.toUser = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
