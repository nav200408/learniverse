package com.example.EnrollmentService.repository;

import com.example.EnrollmentService.model.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Integer> {
}
