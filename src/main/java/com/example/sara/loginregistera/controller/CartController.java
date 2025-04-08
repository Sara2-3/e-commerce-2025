package com.example.sara.loginregistera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.CartItem;
import com.example.sara.loginregistera.model.Product;
import com.example.sara.loginregistera.model.User;
import com.example.sara.loginregistera.repository.CartRepository;
import com.example.sara.loginregistera.service.ProductService;
import com.example.sara.loginregistera.service.StockService;
import com.example.sara.loginregistera.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                          @RequestParam("size") String size,
                          @RequestParam("quantity") int quantity,
                          HttpSession session) {

        Long userId = (Long) session.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return "redirect:/";
        }

        try {
            Product product = productService.findById(productId);
            if (product == null) {
                return "redirect:/home";
            }

            Size sizeEnum = Size.valueOf(size.toUpperCase());
            if (!stockService.isSizeAvailable(productId, sizeEnum, quantity)) {
                return "redirect:/products/details/" + productId + "?error=outofstock&size=" + size;
            }
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setSize(sizeEnum);
            cartItem.setQuantity(quantity);
            cartItem.setUsername(user.getEmail());

            cartRepository.save(cartItem);

            stockService.reduceStock(productId, sizeEnum, quantity);
            
            return "redirect:/cart";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping("")
    public String viewCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return "redirect:/";
        }

        try {
            List<CartItem> cartItems = cartRepository.findByUsername(user.getEmail());
            double totalPrice = cartItems.stream()
                    .mapToDouble(CartItem::getSubtotal)
                    .sum();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            
            return "cart";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }
        CartItem cartItem = cartRepository.findById(id).orElse(null);
        if (cartItem != null) {
            stockService.restoreStock(cartItem.getProduct().getId(), cartItem.getSize(), cartItem.getQuantity());
            cartRepository.deleteById(id);
        }

        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return "redirect:/";
        }
        List<CartItem> userItems = cartRepository.findByUsername(user.getEmail());
        for (CartItem item : userItems) {
            stockService.restoreStock(item.getProduct().getId(), item.getSize(), item.getQuantity());
        }
        cartRepository.deleteAll(userItems);
        return "redirect:/cart";
    }
}