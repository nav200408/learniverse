package com.example.PaymentService.controller;
import com.example.PaymentService.constants.PaymentConstants;
import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.*;
import com.example.PaymentService.service.PaymentService;
import com.example.PaymentService.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    VNPayService vnPayService;

    public PaymentController(PaymentService paymentService, VNPayService vnPayService) {
        this.paymentService = paymentService;
        this.vnPayService = vnPayService;
    }

    @PostMapping("/submit-order")
    public ResponseEntity<PaymentResponse> submitOrder(@RequestBody PaymentSubmitRequest paymentSubmitRequest, HttpServletRequest request){
        return paymentService.paymentHandler(paymentSubmitRequest,request);
    }
    @GetMapping("/vnpay-payment-return")
    public ResponseEntity<PaymentResponse> paymentCompleted(HttpServletRequest request){
        int paymentStatus =vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        boolean status = paymentService.paymentCompleteHanler(paymentStatus,orderInfo,paymentTime,transactionId,totalPrice);
        
        if(status){
            PaymentResponse paymentResponse = new PaymentResponse(PaymentConstants.PAYMENT_RESPONSE_MESSAGE_SUCCESS);
            return ResponseEntity.ok(paymentResponse);
        } else {
            PaymentResponse paymentResponse = new PaymentResponse(PaymentConstants.PAYMENT_RESPONSE_MESSAGE_FAIL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paymentResponse);
        }
    }
}
