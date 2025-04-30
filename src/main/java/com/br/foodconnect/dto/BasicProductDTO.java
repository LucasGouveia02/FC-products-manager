package com.br.foodconnect.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class BasicProductDTO {
    private String name;
    private String description;
    private Double price;
    private Long stock;
    private String category;
    private Long storeId;
    @JsonIgnore
    private MultipartFile image;
}
