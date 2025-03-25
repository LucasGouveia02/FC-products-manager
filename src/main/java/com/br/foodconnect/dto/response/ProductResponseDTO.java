package com.br.foodconnect.dto.response;

import com.br.foodconnect.model.ProductModel;

public class ProductResponseDTO {
    private String name;
    private String description;
    private Double price;
    private Long stock;
    private String imageUrl;
    private CategoryResponseDTO category;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(ProductModel model) {
        this.name = model.getName();
        this.description = model.getDescription();
        this.price = model.getPrice();
        this.stock = model.getStock();
        this.imageUrl = model.getImageUrl();
        this.category = new CategoryResponseDTO(model.getCategory());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryResponseDTO getCategoryId() {
        return category;
    }

    public void setCategoryId(CategoryResponseDTO categoryId) {
        this.category = categoryId;
    }
}
