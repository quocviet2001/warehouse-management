package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}