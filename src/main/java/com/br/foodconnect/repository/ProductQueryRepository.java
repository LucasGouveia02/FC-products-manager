package com.br.foodconnect.repository;

import com.br.foodconnect.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductQueryRepository extends JpaRepository<ProductModel, Long> {

    @Query(value = """
        SELECT 
            p.name AS product_name,
            p.image_url AS product_image,
            c.name AS category_name,
            GROUP_CONCAT(DISTINCT s.food_court ORDER BY s.food_court SEPARATOR ', ') AS available_in_food_courts,
            GROUP_CONCAT(DISTINCT s.name ORDER BY s.name SEPARATOR ', ') AS available_in_stores
        FROM product p
        JOIN category c ON p.product_category_id = c.id
        JOIN store s ON p.store_id = s.id
        GROUP BY p.name, p.image_url, c.name
        ORDER BY p.name
    """, nativeQuery = true)
    List<Map<String, Object>> findProductAvailabilityGroupedByFoodCourt();

    @Query(value = """
        SELECT 
            p.id, 
            p.name, 
            p.description, 
            p.price, 
            p.image_url, 
            p.stock, 
            p.product_category_id
        FROM product p
        WHERE p.store_id = :storeId
    """, nativeQuery = true)
    List<Map<String, Object>> findProductByStore(@Param("storeId") Long storeId);

    @Query(value = """
        SELECT 
            p.id, p.name,
            p.description,
            p.price, p.image_url,
            p.stock,
            p.product_category_id,
            c.expected_delivery_time,
            c.name AS category_name
        FROM product p
        LEFT JOIN category c
        ON p.product_category_id = c.id
        WHERE p.store_id = :storeId AND c.name = :category
    """, nativeQuery = true)
    List<Map<String, Object>> findProductByStoreAndCategory(@Param("storeId") Long storeId,
                                                            @Param("category") String category);

    @Query(value = """
        SELECT 
            s.food_court AS foodCourt,
            s.name AS storeName,
            s.id AS storeId
        FROM product p
        JOIN store s ON p.store_id = s.id
        WHERE p.name = :productName
    """, nativeQuery = true)
    List<Map<String, Object>> findStoresGroupedByFoodCourtByProductName(@Param("productName") String productName);
}
