package com.br.foodconnect.dto.response;

import com.br.foodconnect.model.StoreModel;

public class StoreResponseDTO {
    private Long id;
    private String name;
    private String cnpj;
    private Boolean isEnabled;
    private String FoodCourt;

    public StoreResponseDTO() {
    }

    public StoreResponseDTO(StoreModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.cnpj = model.getCpnj();
        this.isEnabled = model.getEnabled();
        this.FoodCourt = model.getFoodCourt();
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getFoodCourt() {
        return FoodCourt;
    }

    public void setFoodCourt(String foodCourt) {
        FoodCourt = foodCourt;
    }
}
