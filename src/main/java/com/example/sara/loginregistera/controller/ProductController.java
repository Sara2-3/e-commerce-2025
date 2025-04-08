package com.example.sara.loginregistera.controller;

import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.service.ProductService;
import com.example.sara.loginregistera.service.StockService;
import com.example.sara.loginregistera.Size;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;
    @GetMapping("/home")
    public String productList(Model model, HttpSession session, 
            @RequestParam(name = "page", defaultValue = "1") int pageNo) {
        
        int pageSize = 16;
        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("products", products);

        Object userRole = session.getAttribute("userRole");
        model.addAttribute("isAdmin", userRole != null && userRole.toString().equals("ADMIN"));

        return "home";
    }
    @GetMapping("/products/add")
    public String addProductForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        model.addAttribute("product", new Product());
        return "product_form";
    }
    @PostMapping("/products/add")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        productService.saveProduct(product);
        stockService.initializeProductStock(product.getId());

        return "redirect:/home";
    }

    @GetMapping("/products/details/{id}")
    public String productDetails(@PathVariable Long id, Model model,
            @RequestParam(name = "page", defaultValue = "1") int pageNo) {

        Product product = productService.findProductById(id);
        if (product == null) {
            return "redirect:/home";
        }
        int pageSize = 8;
        Page<Product> page = productService.findPaginatedByCategory(pageNo, pageSize, product.getCategory());
        List<Product> relatedProducts = page.getContent();

        List<Map<String, Object>> stockLevels = new ArrayList<>();
        for (Size size : Size.values()) {
            Map<String, Object> stockInfo = new HashMap<>();
            stockInfo.put("size", size.name());
            stockInfo.put("quantity", stockService.getStockQuantity(id, size));
            stockLevels.add(stockInfo);
        }
        model.addAttribute("product", product);
        model.addAttribute("stockLevels", stockLevels);
        model.addAttribute("relatedProducts", relatedProducts);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "product_details";
    }
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        productService.deleteProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        Product product = productService.findProductById(id);
        if (product == null) {
            return "redirect:/home";
        }

        model.addAttribute("product", product);
        return "edit_form";
    }
    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/home";
        }

        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/home";
    }
    private boolean isAdmin(HttpSession session) {
        Object userRole = session.getAttribute("userRole");
        return userRole != null && userRole.toString().equals("ADMIN");
    }
    @GetMapping("/{category}")
    public String viewCategory(@PathVariable String category, 
                         @RequestParam(defaultValue = "1") int page,
                         Model model,
                         HttpSession session) {
        
        int pageSize = 8;
        Page<Product> productPage = productService.findPaginatedByCategory(page, pageSize, category);

        Object userRole = session.getAttribute("userRole");
        model.addAttribute("isAdmin", userRole != null && userRole.toString().equals("ADMIN"));
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("category", category);
        
        return "category_list";
    }
}