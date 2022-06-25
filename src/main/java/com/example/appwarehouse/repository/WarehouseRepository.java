package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

    Optional<Warehouse> findByName(String name);
}
