package com.warehouse.warehouse_backend.repository;

import com.warehouse.warehouse_backend.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface StockOutRepository extends JpaRepository<StockOut, Long> {
    List<StockOut> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
