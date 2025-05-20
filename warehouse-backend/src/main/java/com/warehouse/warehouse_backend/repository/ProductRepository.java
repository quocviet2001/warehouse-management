package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> findByNameOrSkuContainingIgnoreCase(@Param("query") String query);
    List<Product> findByQuantityLessThanEqual(int quantity);
}
