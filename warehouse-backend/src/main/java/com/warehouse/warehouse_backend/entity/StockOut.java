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
    private Product product; 

    @Column(nullable = false)
    private int quantity; 

    @Column(nullable = false)
    private LocalDateTime date; 

    @Column(nullable = false)
    private String reason; 

    private String note; 
}
