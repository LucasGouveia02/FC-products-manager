package com.br.foodconnect.model;

import com.br.foodconnect.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.br.foodconnect.util.Utils.arredondarParaDuasCasasDecimais;


@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 2000)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Long stock;
    private Boolean isEnabled;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private CategoryModel category;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreModel store;

    public ProductModel() {
    }

    public ProductModel(ProductDTO dto, CategoryModel category, StoreModel store, String imageUrl) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.stock = dto.getStock();
        this.price = arredondarParaDuasCasasDecimais(dto.getPrice());
        this.category = category;
        this.store = store;
        this.imageUrl = imageUrl;
    }
    public void setPrice(double price) {
        this.price = arredondarParaDuasCasasDecimais(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }
}
