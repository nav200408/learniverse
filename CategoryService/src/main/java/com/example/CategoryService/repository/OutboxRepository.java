package com.example.CategoryService.repository;

import com.example.CategoryService.enums.OutboxStatus;
import com.example.CategoryService.model.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(OutboxStatus status);
}
