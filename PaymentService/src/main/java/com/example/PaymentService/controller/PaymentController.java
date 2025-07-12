package com.example.PaymentService.controller;

import com.example.PaymentService.client.UserService;
import com.example.PaymentService.dto.request.PaymentSubmitRequest;
import com.example.PaymentService.dto.response.*;
import com.example.PaymentService.model.PaymentEntity;
import com.example.PaymentService.repository.PaymentRepository;
import com.example.PaymentService.service.Impl.VNPayService;
import com.example.PaymentService.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    VNPayService vnPayService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    UserService userService;
    @PostMapping("/submitOrder")
    public ResponseEntity<PaymentResponse> submitOrder(@RequestBody PaymentSubmitRequest paymentSubmitRequest, HttpServletRequest request){
        return paymentService.paymentHandler(paymentSubmitRequest,request);
    }
    @GetMapping("/vnpay-payment-return")
    public ResponseEntity paymentCompleted(HttpServletRequest request){
        int paymentStatus =vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        if(paymentStatus==1){
            PaymentEntity paymentEntity = new PaymentEntity(Integer.parseInt(totalPrice),orderInfo,paymentTime);
           PaymentEntity saveEntity = paymentRepository.saveAndFlush(paymentEntity);
            String username = orderInfo.split(":")[0].trim();
            int courseId =Integer.parseInt(orderInfo.split(":")[1].trim());
            String email = orderInfo.split(":")[2].trim();
            EnrollmentResponse enrollmentResponse = new EnrollmentResponse(paymentEntity.getPaymentId(),username,courseId);
            kafkaTemplate.send("enrollment",enrollmentResponse);
            MessageDTO messageDTO = new MessageDTO(email,"mua khoa hoc thanh cong",username+ "ban mua thanh cong khoa hoc voi id: "+courseId+" voi gia la: "+totalPrice);
            kafkaTemplate.send("notification", messageDTO);
            WishlistDTO wishlistDTO = new WishlistDTO(username,courseId);
            kafkaTemplate.send("wishlist",wishlistDTO);
            URI redirectUri = URI.create("");
            return ResponseEntity.status(HttpStatus.FOUND).body("success");
        }
        URI redirectUri = URI.create("");
        return ResponseEntity.status(HttpStatus.FOUND).body("fail");

    }
}
