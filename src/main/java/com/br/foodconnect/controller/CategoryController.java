package com.br.foodconnect.controller;

import com.br.foodconnect.dto.CategoryDTO;
import com.br.foodconnect.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestBody CategoryDTO dto) throws Exception {
        return categoryService.registerCategory(dto);
    }
}
