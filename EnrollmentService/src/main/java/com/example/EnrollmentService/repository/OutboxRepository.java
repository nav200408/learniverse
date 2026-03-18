package com.example.EnrollmentService.repository;

import com.example.EnrollmentService.enums.OutboxStatus;
import com.example.EnrollmentService.model.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEntity, Integer> {
    List<OutboxEntity> findByStatus(OutboxStatus status);
    void deleteByStatus(OutboxStatus status);
}
