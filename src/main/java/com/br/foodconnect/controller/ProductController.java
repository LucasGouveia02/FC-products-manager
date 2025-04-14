package com.br.foodconnect.controller;

import com.br.foodconnect.dto.ProductDTO;
import com.br.foodconnect.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

    @GetMapping("/list")
    public ResponseEntity<?> listarProdutos(
            @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
            @RequestParam("storeId") Long storeId) {
        return productService.listProducts(page, size, storeId);
    }

    @GetMapping("/listById")
    public ResponseEntity<?> listarProdutoPorId(
            @RequestParam("id") Long id) {
        return productService.listProductById(id);
    }

    @GetMapping("/listByNameAndStoreId")
    public ResponseEntity<?> listarProdutoPorNomeEStoreId(
            @RequestParam("name") String nome,
            @RequestParam("storeId") Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.listProductByNameAndStoreId(nome, storeId, page, size);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestPart("product") ProductDTO dto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam("id") Long id) throws Exception {
        dto.setImage(image);
        return productService.updateProduct(dto, id);
    }

    @PatchMapping("/enable")
    public ResponseEntity<?> enableProduct(
            @RequestParam("id") Long id) {
        return productService.enableProduct(id);
    }

    @PatchMapping("/disable")
    public ResponseEntity<?> disableProduct(
            @RequestParam("id") Long id) {
        return productService.disableProduct(id);
    }
}
