package com.coffeelink.apicore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Column(nullable = true)
    @Size(max = 1000, message = "La descripción es demasiado larga (máx 1000 caracteres)")
    private String descripcion;

    @Column(nullable = false)
    @Min(value = 1, message = "El precio debe ser al menos 1 CLP")
    @Max(value = 9999999, message = "El precio es demasiado alto (máx 9.9M)")
    @Digits(integer = 7, fraction = 0, message = "El precio debe ser un número entero (sin decimales)")
    private BigDecimal precio;

    @Column(nullable = false)
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Max(value = 10000, message = "El stock no puede ser mayor a 10,000")
    private Integer stock;

    @Column(name = "imagen_url")
    @Size(max = 1000, message = "La URL de la imagen es demasiado larga")
    private String imagenUrl;

    @Column(name = "fecha_creacion", updatable = false)
    private Instant fechaCreacion = Instant.now();

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion = Instant.now();
}