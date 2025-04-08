package com.example.sara.loginregistera.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Description is required.")
    @Column(length = 500)
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    private String imageUrl;

    @NotBlank(message = "Category is required.")
    private String category;


    private int stockS = 5;
    private int stockM = 5;
    private int stockL = 5;
    private int stockXL = 5;

    public int getStockS() {
        return stockS;
    }

    public void setStockS(int stockS) {
        this.stockS = stockS;
    }

    public int getStockM() { return stockM; }
    public void setStockM(int stockM) { this.stockM = stockM; }

    public int getStockL() { return stockL; }
    public void setStockL(int stockL) { this.stockL = stockL; }

    public int getStockXL() { return stockXL; }
    public void setStockXL(int stockXL) { this.stockXL = stockXL; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

}