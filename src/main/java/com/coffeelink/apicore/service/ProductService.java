package com.coffeelink.apicore.service;

import com.coffeelink.apicore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coffeelink.apicore.repository.ProductRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        if (product.getPrecio() == null || product.getPrecio().doubleValue() <= 0 ||
            product.getStock() == null || product.getStock() < 0){
            throw new IllegalArgumentException("Invalid product data");
        }
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setNombre(productDetails.getNombre());
                    product.setDescripcion(productDetails.getDescripcion());
                    product.setPrecio(productDetails.getPrecio());
                    product.setStock(productDetails.getStock());
                    product.setFechaCreacion(Instant.now());
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
