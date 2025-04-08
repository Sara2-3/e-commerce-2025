package com.example.sara.loginregistera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.Stock;
import com.example.sara.loginregistera.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    private static final int INITIAL_STOCK = 5;

    @Transactional
    public void initializeProductStock(Long productId) {
        // Initialize stock for each size with 5 items
        for (Size size : Size.values()) {
            Stock stock = stockRepository.findByProductIdAndSize(productId, size);
            if (stock == null) {
                stock = new Stock();
                stock.setProductId(productId);
                stock.setSize(size);
                stock.setQuantity(INITIAL_STOCK);
                stockRepository.save(stock);
            }
        }
    }

    public boolean isSizeAvailable(Long productId, Size size, int quantity) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        if (stock == null) {
            initializeStock(productId, size);
            return true;
        }
        return stock.getQuantity() >= quantity;
    }

    @Transactional
    public void reduceStock(Long productId, Size size, int quantity) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        if (stock == null) {
            initializeStock(productId, size);
            stock = stockRepository.findByProductIdAndSize(productId, size);
        }
        
        if (stock.getQuantity() >= quantity) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);
        }
    }

    @Transactional
    public void restoreStock(Long productId, Size size, int quantity) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        if (stock == null) {
            initializeStock(productId, size);
            return;
        }
        
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);
    }

    public int getStockQuantity(Long productId, Size size) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        if (stock == null) {
            initializeStock(productId, size);
            return INITIAL_STOCK;
        }
        return stock.getQuantity();
    }

    @Transactional
    private void initializeStock(Long productId, Size size) {
        Stock stock = new Stock();
        stock.setProductId(productId);
        stock.setSize(size);
        stock.setQuantity(INITIAL_STOCK);
        stockRepository.save(stock);
    }

    public Object getLowStockProducts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}