package com.warehouse.warehouse_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_out")
@Data
public class StockOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Sản phẩm được xuất

    @Column(nullable = false)
    private int quantity; // Số lượng xuất

    @Column(nullable = false)
    private LocalDateTime date; // Ngày xuất

    @Column(nullable = false)
    private String reason; // Lý do xuất (ví dụ: bán hàng, hủy hỏng)

    private String note; // Ghi chú (tùy chọn)
}
