package com.br.foodconnect.service;

import com.br.foodconnect.dto.ErrorResponseDTO;
import com.br.foodconnect.dto.ProductDTO;
import com.br.foodconnect.dto.response.ProductResponseDTO;
import com.br.foodconnect.model.CategoryModel;
import com.br.foodconnect.model.ProductModel;
import com.br.foodconnect.model.StoreModel;
import com.br.foodconnect.repository.CategoryRepository;
import com.br.foodconnect.repository.ProductRepository;
import com.br.foodconnect.repository.StoreRepository;
import com.br.foodconnect.util.BlobStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private BlobStorageUtil blobStorageUtil;

    public ResponseEntity<?> registerProduct(ProductDTO dto) throws Exception {

        if (dto.getCategory() == null || dto.getCategory().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça uma categoria!"));
        }

        if (dto.getStoreId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça uma loja!"));
        }
        CategoryModel categoryModel = categoryRepository.findByName(dto.getCategory());
        StoreModel storeModel = storeRepository.findById(dto.getStoreId()).orElse(null);

        if (categoryModel == null && storeModel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("A loja e a categoria fornecidas não existem!"));
        } else if (categoryModel == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO("Forneça uma categoria existente!"));
        } else if (storeModel == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO("Forneça uma loja existente!"));
        }
        String imageUrl = null;
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            imageUrl = blobStorageUtil.uploadImage(dto.getImage());
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Forneça a imagem do produto!"));
        }

        ProductModel productModel = new ProductModel(dto, categoryModel, storeModel, imageUrl);
        productModel.setEnabled(true);
        productModel = productRepository.save(productModel);
        ProductResponseDTO responseDTO = new ProductResponseDTO(productModel);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<Map<String, Object>> listProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductModel> produtoPage = productRepository.findAll(pageable);
        List<ProductResponseDTO> produtos = produtoPage.stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("products", produtos);
        response.put("totalPages", produtoPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
