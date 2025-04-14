package com.br.foodconnect.repository;

import com.br.foodconnect.model.ProductModel;
import com.br.foodconnect.model.StoreModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Page<ProductModel> findAllByStoreId(Pageable pageable, Long storeId);

    @Query("SELECT p FROM ProductModel p WHERE p.name LIKE %?1% AND p.store = ?2")
    Page<ProductModel> findAllByNameAndStoreId(String name, StoreModel storeId, Pageable pageable);
}
