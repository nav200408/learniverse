package com.example.EmailService.dto;

public class MessageDTO {
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

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
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
