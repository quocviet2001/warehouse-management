package com.warehouse.warehouse_backend.controller;

import com.warehouse.warehouse_backend.dto.StockOutDTO;
import com.warehouse.warehouse_backend.service.StockOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-out")
public class StockOutController {

    @Autowired
    private StockOutService stockOutService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<StockOutDTO> createStockOut(@RequestBody StockOutDTO stockOutDTO) {
        return ResponseEntity.ok(stockOutService.createStockOut(stockOutDTO));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<StockOutDTO>> getStockOuts() {
        return ResponseEntity.ok(stockOutService.getStockOuts());
    }
}
