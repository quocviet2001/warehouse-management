package com.warehouse.warehouse_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockInDTO {
    private Long id;
    private Long productId; // ID sản phẩm
    private String productSku;
    private String productName;
    private int quantity; // Số lượng nhập
    private LocalDateTime date; // Ngày nhập
    private String reason; // Lý do
    private String note; // Ghi chú
}
