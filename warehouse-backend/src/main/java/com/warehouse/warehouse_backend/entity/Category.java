package com.warehouse.warehouse_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Tên danh mục

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products; // Danh sách sản phẩm thuộc danh mục
}