package com.br.foodconnect.service;

import com.br.foodconnect.dto.CategoryDTO;
import com.br.foodconnect.dto.response.CategoryResponseDTO;
import com.br.foodconnect.model.CategoryModel;
import com.br.foodconnect.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> registerCategory(CategoryDTO dto) {
        CategoryModel categoryModel = new CategoryModel(dto);
        categoryModel = categoryRepository.save(categoryModel);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoryModel);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
