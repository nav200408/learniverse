package com.example.PaymentService.service.Impl;

import com.example.PaymentService.event.EnrollmentEvent;
import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.PaymentResponse;
import com.example.PaymentService.model.OutboxEntity;
import com.example.PaymentService.model.PaymentEntity;
import com.example.PaymentService.repository.OutboxRepository;
import com.example.PaymentService.repository.PaymentRepository;
import com.example.PaymentService.service.PaymentService;
import com.example.PaymentService.service.VNPayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.PaymentService.enums.OutboxStatus;
import com.example.PaymentService.constants.PaymentConstants;
import com.example.PaymentService.exception.PaymentProcessingException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    VNPayService vnPayService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OutboxRepository outboxRepository;

    public PaymentServiceImpl(VNPayService vnPayService, PaymentRepository paymentRepository) {
        this.vnPayService = vnPayService;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<PaymentResponse> paymentHandler(PaymentSubmitRequest paymentSubmitRequest,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + "/payment";
        String vnpayUrl = vnPayService.createOrder(request, (int) paymentSubmitRequest.getAmount(),
                paymentSubmitRequest.getOrderInfo(), baseUrl);
        return ResponseEntity.ok().body(new PaymentResponse(vnpayUrl));
    }

    public void paymentUpdateHandler(int paymentId, String status) {
        paymentRepository.updatePaymentStatus(paymentId, status);
    }

    @Transactional
    @Override
    public boolean paymentCompleteHanler(int paymentStatus, String orderInfo, String paymentTime, String transactionId,
            String totalPrice) {
        if (paymentStatus == 1) {
            ObjectMapper objectMapper = new ObjectMapper();
            PaymentEntity paymentEntity = new PaymentEntity(Integer.parseInt(totalPrice), orderInfo, paymentTime,
                    "PENDING");
            String username = orderInfo.split(":")[0].trim();
            int courseId = Integer.parseInt(orderInfo.split(":")[1].trim());
            String email = orderInfo.split(":")[2].trim();

            paymentRepository.saveAndFlush(paymentEntity);
            EnrollmentEvent enrollmentEvent = new EnrollmentEvent(paymentEntity.getPaymentId(), username, courseId,
                    email);
            OutboxEntity outboxEnroll = new OutboxEntity();
            outboxEnroll.setTopic(PaymentConstants.KAFKA_TOPIC_PAYMENT_CREATED_SUCCESS);
            outboxEnroll.setStatus(OutboxStatus.PENDING);
            try {
                outboxEnroll.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
            } catch (JsonProcessingException e) {
                throw new PaymentProcessingException("Failed to serialize enrollment event", e);
            }
            outboxRepository.save(outboxEnroll);
            return true;
        }
        return false;
    }
}
