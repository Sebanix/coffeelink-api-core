package com.coffeelink.apicore.service;

import com.coffeelink.apicore.model.Product;
import com.coffeelink.apicore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(String nombre, BigDecimal precioMin, BigDecimal precioMax, Pageable pageable) {

        BigDecimal max = (precioMax != null && precioMax.doubleValue() > 0) ? precioMax : null;

        return productRepository.searchProducts(nombre, precioMin, max, pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setNombre(productDetails.getNombre());
                    product.setDescripcion(productDetails.getDescripcion());
                    product.setPrecio(productDetails.getPrecio());
                    product.setStock(productDetails.getStock());
                    product.setFechaActualizacion(Instant.now());
                    return productRepository.save(product);
                });
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}