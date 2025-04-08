package com.example.sara.loginregistera.service;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CartService {

    private final List<CartItem> cartItems = new ArrayList<>(); // Stores cart items

    // Add a product to the cart
    public void addToCart(CartItem cartItem) {
        for (CartItem item : cartItems) {
            // If the same product with the same size already exists, increase quantity
            if (item.getProduct().getId().equals(cartItem.getProduct().getId()) && item.getSize() == cartItem.getSize()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        cartItems.add(cartItem); // If not exists, add as a new item
    }

    // Get all items in the cart
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Calculate the total price of the cart
    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }

    // Remove a specific product from the cart by ID and size
    public void removeFromCart(Long productId, Size size) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId().equals(productId) && item.getSize() == size) {
                iterator.remove();
                break;
            }
        }
    }

    // Clear all items in the cart
    public void clearCart() {
        cartItems.clear();
    }
}