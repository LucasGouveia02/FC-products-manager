package com.br.foodconnect.controller;

import com.br.foodconnect.dto.ProductDTO;
import com.br.foodconnect.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(
            @RequestPart("product") ProductDTO dto,
            @RequestPart("image") MultipartFile image) throws Exception {
        dto.setImage(image);
        return productService.registerProduct(dto);
    }
}
