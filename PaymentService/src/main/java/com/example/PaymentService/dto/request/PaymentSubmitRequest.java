package com.example.PaymentService.dto.request;

public class PaymentSubmitRequest {
    private String orderInfo;
    private int amount;

    public PaymentSubmitRequest() {
    }

    public PaymentSubmitRequest(String orderInfo, int amount) {
        this.orderInfo = orderInfo;
        this.amount = amount;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
