package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);
    Category findByParentCategoryId(Integer parentCategory_id);
}
