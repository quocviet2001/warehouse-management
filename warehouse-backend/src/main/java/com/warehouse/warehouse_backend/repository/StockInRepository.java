package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface StockInRepository extends JpaRepository<StockIn, Long> {
    List<StockIn> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
