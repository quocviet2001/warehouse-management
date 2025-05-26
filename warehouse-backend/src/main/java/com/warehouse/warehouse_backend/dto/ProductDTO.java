package com.warehouse.warehouse_backend.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name; 
    private String sku; 
    private String unit;  
    private int quantity; 
    private double price;
    private Long categoryId; 
    private String categoryName; 
}
