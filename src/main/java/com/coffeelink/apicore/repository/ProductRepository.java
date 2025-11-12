package com.coffeelink.apicore.repository;

import com.coffeelink.apicore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    @Query(value = "SELECT * FROM productos p WHERE " +
            "(:nombre IS NULL OR lower(unaccent(p.nombre)) LIKE lower(unaccent(concat('%', :nombre, '%')))) AND " +
            "(:precioMin IS NULL OR p.precio >= :precioMin) AND " +
            "(:precioMax IS NULL OR p.precio <= :precioMax)",
            countQuery = "SELECT count(*) FROM productos p WHERE " +
                    "(:nombre IS NULL OR lower(unaccent(p.nombre)) LIKE lower(unaccent(concat('%', :nombre, '%')))) AND " +
                    "(:precioMin IS NULL OR p.precio >= :precioMin) AND " +
                    "(:precioMax IS NULL OR p.precio <= :precioMax)",
            nativeQuery = true)
    Page<Product> searchProducts(
            @Param("nombre") String nombre,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            Pageable pageable
    );

    Page<Product> findAll(Pageable pageable);
}