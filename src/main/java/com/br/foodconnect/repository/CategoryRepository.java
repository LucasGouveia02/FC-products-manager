package com.br.foodconnect.repository;

import com.br.foodconnect.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    CategoryModel findByName(String name);
}
