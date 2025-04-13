package com.br.foodconnect.repository;

import com.br.foodconnect.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Page<ProductModel> findAllByStoreId(Pageable pageable, Long storeId);
}
