package com.example.PaymentService.repository;

import com.example.PaymentService.dto.response.EnrollmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentDtoRepository extends JpaRepository<EnrollmentResponse,Integer> {
}
