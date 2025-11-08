package com.coffeelink.apicore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "pedidos")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_pedido", updatable = false)
    private Instant fechaPedido = Instant.now();

}
