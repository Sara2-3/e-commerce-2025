package com.example.sara.loginregistera.service;

import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepo.findByCategoryIgnoreCase(category);
    }

    public Product findProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product findById(Long productId) {
        return productRepo.findById(productId).orElse(null);
    }

    // Check inventory for a specific size
    public boolean isStockAvailable(Product product, String size, int quantity) {
        switch (size.toUpperCase()) {
            case "S": return product.getStockS() >= quantity;
            case "M": return product.getStockM() >= quantity;
            case "L": return product.getStockL() >= quantity;
            case "XL": return product.getStockXL() >= quantity;
            default: return false;
        }
    }

    // Deduct inventory for a specific size
    public void updateStock(Product product, String size, int quantity) {
        switch (size.toUpperCase()) {
            case "S":
                product.setStockS(product.getStockS() - quantity);
                break;
            case "M":
                product.setStockM(product.getStockM() - quantity);
                break;
            case "L":
                product.setStockL(product.getStockL() - quantity);
                break;
            case "XL":
                product.setStockXL(product.getStockXL() - quantity);
                break;
        }
        saveProduct(product); // Persist changes
    }

    // Add this new method for pagination
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return productRepo.findAll(pageable);
    }

    public Page<Product> findPaginatedByCategory(int pageNo, int pageSize, String category) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return productRepo.findByCategoryIgnoreCase(category, pageable);
    }

//    public List<Product> findProductsByCategoryNew(String category) {
//        return productRepo.findByCategory(category);
//    }
}
