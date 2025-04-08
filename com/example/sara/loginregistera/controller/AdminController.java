package com.example.sara.loginregistera.controller;


import com.example.sara.loginregistera.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("lowStockProducts", stockService.getLowStockProducts());
        return "admin/dashboard";
    }

    @GetMapping("/stock")
    public String manageStock(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/stock";
    }
} 