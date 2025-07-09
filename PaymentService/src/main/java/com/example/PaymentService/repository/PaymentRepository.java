package com.example.PaymentService.repository;

import com.example.PaymentService.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
}
