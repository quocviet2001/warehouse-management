package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOutRepository extends JpaRepository<StockOut, Long> {
}