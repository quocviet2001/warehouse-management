package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.dto.StockInDTO;
import com.warehouse.warehouse_backend.dto.StockOutDTO;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import com.warehouse.warehouse_backend.repository.StockInRepository;
import com.warehouse.warehouse_backend.repository.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<StockInDTO> getInOutReport(LocalDateTime startDate, LocalDateTime endDate) {
        return stockInRepository.findAll().stream()
                .filter(stockIn -> stockIn.getDate().isAfter(startDate) && stockIn.getDate().isBefore(endDate))
                .map(stockIn -> {
                    StockInDTO dto = new StockInDTO();
                    dto.setId(stockIn.getId());
                    dto.setProductId(stockIn.getProduct().getId());
                    dto.setQuantity(stockIn.getQuantity());
                    dto.setDate(stockIn.getDate());
                    dto.setReason(stockIn.getReason());
                    dto.setNote(stockIn.getNote());
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<ProductDTO> getStockStatusReport() {
        return productRepository.findAll().stream()
                .filter(product -> product.getQuantity() > 100 || product.getQuantity() < 10) // Hàng tồn nhiều (>100) hoặc sắp hết (<10)
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setSku(product.getSku());
                    dto.setUnit(product.getUnit());
                    dto.setQuantity(product.getQuantity());
                    dto.setPrice(product.getPrice());
                    dto.setCategoryId(product.getCategory().getId());
                    return dto;
                }).collect(Collectors.toList());
    }
}
