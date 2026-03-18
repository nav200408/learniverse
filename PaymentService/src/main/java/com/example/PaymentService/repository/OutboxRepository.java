package com.example.PaymentService.repository;

import com.example.PaymentService.enums.OutboxStatus;
import com.example.PaymentService.model.OutboxEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEntity,Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public List<OutboxEntity> findByStatus(OutboxStatus status);

    @Modifying
    @Query("DELETE FROM OutboxEntity o WHERE o.status = :status")
    public void deleteByStatus(OutboxStatus status);
}
