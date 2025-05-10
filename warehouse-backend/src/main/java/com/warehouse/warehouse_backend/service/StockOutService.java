package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.StockOutDTO;
import com.warehouse.warehouse_backend.entity.Product;
import com.warehouse.warehouse_backend.entity.StockOut;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import com.warehouse.warehouse_backend.repository.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockOutService {

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private ProductRepository productRepository;

    public StockOutDTO createStockOut(StockOutDTO stockOutDTO) {
        Product product = productRepository.findById(stockOutDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < stockOutDTO.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        StockOut stockOut = new StockOut();
        stockOut.setProduct(product);
        stockOut.setQuantity(stockOutDTO.getQuantity());
        stockOut.setDate(stockOutDTO.getDate());
        stockOut.setReason(stockOutDTO.getReason());
        stockOut.setNote(stockOutDTO.getNote());

        // Cập nhật số lượng tồn kho
        product.setQuantity(product.getQuantity() - stockOutDTO.getQuantity());
        productRepository.save(product);

        stockOut = stockOutRepository.save(stockOut);

        stockOutDTO.setId(stockOut.getId());
        return stockOutDTO;
    }

    public List<StockOutDTO> getStockOuts() {
        return stockOutRepository.findAll().stream().map(stockOut -> {
            StockOutDTO dto = new StockOutDTO();
            dto.setId(stockOut.getId());
            dto.setProductId(stockOut.getProduct().getId());
            dto.setQuantity(stockOut.getQuantity());
            dto.setDate(stockOut.getDate());
            dto.setReason(stockOut.getReason());
            dto.setNote(stockOut.getNote());
            return dto;
        }).collect(Collectors.toList());
    }
}