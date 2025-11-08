package com.coffeelink.apicore.repository;

import com.coffeelink.apicore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
