package com.coffeelink.apicore.controller;

import com.coffeelink.apicore.model.Order;
import com.coffeelink.apicore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        try {
            Order newOrder = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        } catch (Exception e) {
            if (e.getMessage().contains("stock")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
