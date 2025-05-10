package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name); // Tìm theo tên
    List<Product> findBySkuContainingIgnoreCase(String sku); // Tìm theo SKU
}
