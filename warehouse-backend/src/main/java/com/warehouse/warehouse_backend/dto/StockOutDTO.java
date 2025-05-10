package com.warehouse.warehouse_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockOutDTO {
    private Long id;
    private Long productId; // ID sản phẩm
    private int quantity; // Số lượng xuất
    private LocalDateTime date; // Ngày xuất
    private String reason; // Lý do
    private String note; // Ghi chú
}
