package com.warehouse.warehouse_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockInDTO {
    private Long id;
    private Long productId; 
    private String productSku;
    private String productName;
    private int quantity; 
    private LocalDateTime date; 
    private String reason; 
    private String note; 
}
