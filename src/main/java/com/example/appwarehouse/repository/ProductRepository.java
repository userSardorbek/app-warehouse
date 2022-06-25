package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameAndCategoryId(String name, Integer category_id);
}
