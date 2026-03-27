package com.example.EmailService.event;

public class EnrollmentEvent {
    private int paymentId;
    private String username;
    private int courseId;
    private String email;

    public EnrollmentEvent() {
    }

    public EnrollmentEvent(int paymentId, String username, int courseId, String email) {
        this.paymentId = paymentId;
        this.username = username;
        this.courseId = courseId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
