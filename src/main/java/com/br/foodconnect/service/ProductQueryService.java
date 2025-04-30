package com.br.foodconnect.service;

import com.br.foodconnect.repository.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductQueryService {

    @Autowired
    private ProductQueryRepository productQueryRepository;

    public List<Map<String, Object>> listProductByFoodCourt() {
        return productQueryRepository.findProductAvailabilityGroupedByFoodCourt();
    }

    public List<Map<String, Object>> listProductByStore(Long storeId) {
        return productQueryRepository.findProductByStore(storeId);
    }

    public List<Map<String, Object>> listProductByStoreAndCategory(Long storeId, String category) {
        return productQueryRepository.findProductByStoreAndCategory(storeId, category);
    }

    public List<Map<String, Object>> listStoresGroupedByFoodCourtByProductName(String productName) {
        return productQueryRepository.findStoresGroupedByFoodCourtByProductName(productName);
    }
}
