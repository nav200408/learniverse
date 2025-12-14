package com.example.PaymentService.repository;

import com.example.PaymentService.dto.response.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDtoRepository extends JpaRepository<MessageDTO,Integer> {
}
