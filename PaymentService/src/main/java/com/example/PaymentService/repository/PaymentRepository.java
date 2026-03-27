package com.example.PaymentService.repository;

import com.example.PaymentService.model.PaymentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE PaymentEntity p SET p.status = :status WHERE p.id = :paymentId")
    void updatePaymentStatus(@Param("paymentId") int paymentId, @Param("status") String status);
}
