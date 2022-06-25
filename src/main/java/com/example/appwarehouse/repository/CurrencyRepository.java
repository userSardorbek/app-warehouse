package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByName(String name);
}
