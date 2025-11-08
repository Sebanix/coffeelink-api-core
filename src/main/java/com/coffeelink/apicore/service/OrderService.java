package com.coffeelink.apicore.service;

import com.coffeelink.apicore.model.Order;
import com.coffeelink.apicore.model.Product;
import com.coffeelink.apicore.repository.OrderRepository;
import com.coffeelink.apicore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(Order order) throws Exception {

        Product product = productRepository.findById(order.getProductoId())
                .orElseThrow(() -> new Exception("Producto no encontrado"));

        if (product.getStock() < order.getCantidad()) {
            throw new Exception("No hay stock suficiente");
        }

        product.setStock(product.getStock() - order.getCantidad());
        productRepository.save(product);

        return orderRepository.save(order);
    }
}
