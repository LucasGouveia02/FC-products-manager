package com.br.foodconnect.dto.response;

import com.br.foodconnect.model.CategoryModel;

public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String expectedDeliveryTime;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(CategoryModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.expectedDeliveryTime = model.getExpectedDeliveryTime();
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

    public String getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }
}
