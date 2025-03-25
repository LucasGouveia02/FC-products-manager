package com.br.foodconnect.service;

import com.br.foodconnect.dto.ErrorResponseDTO;
import com.br.foodconnect.dto.ProductDTO;
import com.br.foodconnect.dto.response.ProductResponseDTO;
import com.br.foodconnect.model.CategoryModel;
import com.br.foodconnect.model.ProductModel;
import com.br.foodconnect.repository.CategoryRepository;
import com.br.foodconnect.repository.ProductRepository;
import com.br.foodconnect.util.BlobStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BlobStorageUtil blobStorageUtil;

    public ResponseEntity<?> registerProduct(ProductDTO dto) throws Exception {

        String imageUrl = null;
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            imageUrl = blobStorageUtil.uploadImage(dto.getImage());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça a imagem do produto!"));
        }

        CategoryModel categoryModel = categoryRepository.findById(dto.getCategoryId()).orElse(null);

        if (categoryModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça uma categoria válida!"));
        }

        ProductModel productModel = new ProductModel(dto, categoryModel, imageUrl);
        productModel = productRepository.save(productModel);
        ProductResponseDTO responseDTO = new ProductResponseDTO(productModel);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
