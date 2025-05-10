package com.warehouse.warehouse_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_in")
@Data
public class StockIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Sản phẩm được nhập

    @Column(nullable = false)
    private int quantity; // Số lượng nhập

    @Column(nullable = false)
    private LocalDateTime date; // Ngày nhập

    @Column(nullable = false)
    private String reason; // Lý do nhập (ví dụ: mua mới)

    private String note; // Ghi chú (tùy chọn)
}
