package com.example.CourseService.repository;

import com.example.CourseService.enums.OutboxStatus;
import com.example.CourseService.model.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(OutboxStatus status);
}
