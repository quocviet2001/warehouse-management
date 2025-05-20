package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.StockInDTO;
import com.warehouse.warehouse_backend.entity.Product;
import com.warehouse.warehouse_backend.entity.StockIn;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import com.warehouse.warehouse_backend.repository.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockInService {

    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private ProductRepository productRepository;

    public StockInDTO createStockIn(StockInDTO stockInDTO) {
        Product product = productRepository.findById(stockInDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StockIn stockIn = new StockIn();
        stockIn.setProduct(product);
        stockIn.setQuantity(stockInDTO.getQuantity());
        stockIn.setDate(stockInDTO.getDate());
        stockIn.setReason(stockInDTO.getReason());
        stockIn.setNote(stockInDTO.getNote());

        // Cập nhật số lượng tồn kho
        product.setQuantity(product.getQuantity() + stockInDTO.getQuantity());
        productRepository.save(product);

        stockIn = stockInRepository.save(stockIn);

        stockInDTO.setProductSku(product.getSku());
        stockInDTO.setProductName(product.getName());
        stockInDTO.setId(stockIn.getId());
        return stockInDTO;
    }

    public List<StockInDTO> getStockIns() {
        return stockInRepository.findAll().stream().map(stockIn -> {
            StockInDTO dto = new StockInDTO();
            dto.setId(stockIn.getId());
            dto.setProductId(stockIn.getProduct().getId());
            dto.setProductSku(stockIn.getProduct().getSku());
            dto.setProductName(stockIn.getProduct().getName());
            dto.setQuantity(stockIn.getQuantity());
            dto.setDate(stockIn.getDate());
            dto.setReason(stockIn.getReason());
            dto.setNote(stockIn.getNote());
            return dto;
        }).collect(Collectors.toList());
    }
}
