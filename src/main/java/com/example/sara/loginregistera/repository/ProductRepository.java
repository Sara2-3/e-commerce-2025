package com.example.sara.loginregistera.repository;

import com.example.sara.loginregistera.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    List<Product> findByCategoryIgnoreCase(String category);
    Page<Product> findByCategoryIgnoreCase(String category, Pageable pageable);

}