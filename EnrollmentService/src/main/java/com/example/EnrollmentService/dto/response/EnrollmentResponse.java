package com.example.EnrollmentService.dto.response;

public class EnrollmentResponse {
    private int paymentId;
    private String username;
    private int courseId;

    public EnrollmentResponse() {
    }

    public EnrollmentResponse(int paymentId, String username, int courseId) {
        this.paymentId = paymentId;
        this.username = username;
        this.courseId = courseId;
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
