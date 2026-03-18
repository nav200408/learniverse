package com.example.PaymentService.service;

import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.PaymentResponse;
import com.example.PaymentService.model.PaymentEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<PaymentResponse> paymentHandler(PaymentSubmitRequest paymentSubmitRequest,
            HttpServletRequest request);

    public PaymentEntity paymentUpdateHandler(int orderId, String status);

    public boolean paymentCompleteHanler(int paymentStatus, String orderInfo, String paymentTime, String transactionId,
            String totalPrice);

}
