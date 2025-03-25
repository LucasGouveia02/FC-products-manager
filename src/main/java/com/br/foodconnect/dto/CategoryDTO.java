package com.br.foodconnect.dto;

import com.br.foodconnect.model.CategoryModel;

public class CategoryDTO {
    private String name;
    private String expectedDeliveryTime;

    public CategoryDTO() {
    }

    public CategoryDTO(CategoryModel model) {
        this.name = model.getName();
        this.expectedDeliveryTime = model.getExpectedDeliveryTime();
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
