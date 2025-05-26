package com.warehouse.warehouse_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; 

    @Column(nullable = false, unique = true)
    private String sku; 

    @Column(nullable = false)
    private String unit; 

    @Column(nullable = false)
    private int quantity; 

    @Column(nullable = false)
    private double price; 

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; 
}
