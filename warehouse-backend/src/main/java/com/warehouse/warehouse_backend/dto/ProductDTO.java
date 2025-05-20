package com.warehouse.warehouse_backend.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name; // Tên sản phẩm
    private String sku; // Mã sản phẩm
    private String unit; // Đơn vị tính
    private int quantity; // Số lượng
    private double price; // Giá
    private Long categoryId; // ID danh mục
    private String categoryName; // Tên danh mục
}
