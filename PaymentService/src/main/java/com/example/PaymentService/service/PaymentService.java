package com.example.PaymentService.service;

import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<PaymentResponse> paymentHandler(PaymentSubmitRequest paymentSubmitRequest, HttpServletRequest request);
}
