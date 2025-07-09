package com.example.PaymentService.service.Impl;

import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.PaymentResponse;
import com.example.PaymentService.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    VNPayService vnPayService;
    @Override
    public ResponseEntity<PaymentResponse> paymentHandler(PaymentSubmitRequest paymentSubmitRequest, HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/payment";
        String vnpayUrl = vnPayService.createOrder(request, (int) paymentSubmitRequest.getAmount(), paymentSubmitRequest.getOrderInfo(), baseUrl);
        return ResponseEntity.ok().body(new PaymentResponse(vnpayUrl));
    }
}
