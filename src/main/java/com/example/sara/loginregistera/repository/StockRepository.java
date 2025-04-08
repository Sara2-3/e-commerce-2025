package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductIdAndSize(Long productId, Size size);
}
