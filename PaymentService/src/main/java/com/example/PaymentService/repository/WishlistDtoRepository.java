package com.example.PaymentService.repository;

import com.example.PaymentService.dto.response.WishlistDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistDtoRepository extends JpaRepository<WishlistDTO,Integer> {
}
