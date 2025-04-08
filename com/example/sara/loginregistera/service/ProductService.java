package com.example.sara.loginregistera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.sara.loginregistera.model.Product;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteProduct(Long id) {
        try {
            // Fetch the product with its associations
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                // Clear associations
                product.getStocks().clear();
                product.getCartItems().clear();
                
                // Flush changes
                entityManager.flush();
                
                // Remove the product
                entityManager.remove(product);
                entityManager.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage(), e);
        }
    }
} 