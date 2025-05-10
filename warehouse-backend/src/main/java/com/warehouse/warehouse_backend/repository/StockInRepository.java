package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockInRepository extends JpaRepository<StockIn, Long> {
}
