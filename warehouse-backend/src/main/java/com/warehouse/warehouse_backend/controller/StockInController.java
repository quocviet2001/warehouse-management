package com.warehouse.warehouse_backend.controller;

import com.warehouse.warehouse_backend.dto.StockInDTO;
import com.warehouse.warehouse_backend.service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-in")
public class StockInController {

    @Autowired
    private StockInService stockInService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<StockInDTO> createStockIn(@RequestBody StockInDTO stockInDTO) {
        return ResponseEntity.ok(stockInService.createStockIn(stockInDTO));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<StockInDTO>> getStockIns() {
        return ResponseEntity.ok(stockInService.getStockIns());
    }
}
