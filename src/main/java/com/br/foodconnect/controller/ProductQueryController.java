package com.br.foodconnect.controller;

import com.br.foodconnect.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductQueryController {

    @Autowired
    private ProductQueryService productQueryService;

    @GetMapping("/distinct-query")
    public List<Map<String, Object>> listProductByFoodCourt() {
        return productQueryService.listProductByFoodCourt();
    }

    @GetMapping("/store-query")
    public List<Map<String, Object>> listProductByStore(@RequestParam Long storeId) {
        return productQueryService.listProductByStore(storeId);
    }

    @GetMapping("/category-query")
    public List<Map<String, Object>> listProductByCategory(@RequestParam Long storeId,
                                                           @RequestParam String category) {
        return productQueryService.listProductByStoreAndCategory(storeId, category);
    }
}
