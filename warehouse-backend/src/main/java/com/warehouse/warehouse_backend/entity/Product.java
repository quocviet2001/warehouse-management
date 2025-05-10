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
    private String name; // Tên sản phẩm

    @Column(nullable = false, unique = true)
    private String sku; // Mã sản phẩm (SKU)

    @Column(nullable = false)
    private String unit; // Đơn vị tính (ví dụ: cái, kg)

    @Column(nullable = false)
    private int quantity; // Số lượng tồn kho

    @Column(nullable = false)
    private double price; // Giá sản phẩm

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Danh mục sản phẩm
}
