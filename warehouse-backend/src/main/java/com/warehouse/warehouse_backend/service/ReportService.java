package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.InOutReportDTO;
import com.warehouse.warehouse_backend.entity.StockIn;
import com.warehouse.warehouse_backend.entity.StockOut;
import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import com.warehouse.warehouse_backend.repository.StockInRepository;
import com.warehouse.warehouse_backend.repository.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<InOutReportDTO> getInOutReport(LocalDateTime startDate, LocalDateTime endDate) {
        // Lấy dữ liệu StockIn
        List<StockIn> stockIns = stockInRepository.findByDateBetween(startDate, endDate);
        List<InOutReportDTO> inReport = stockIns.stream().map(stockIn -> {
            InOutReportDTO dto = new InOutReportDTO();
            dto.setId(stockIn.getId());
            dto.setProductId(stockIn.getProduct().getId());
            dto.setQuantity(stockIn.getQuantity());
            dto.setDate(stockIn.getDate());
            dto.setReason(stockIn.getReason());
            dto.setNote(stockIn.getNote());
            dto.setType("STOCK_IN");
            return dto;
        }).collect(Collectors.toList());

        // Lấy dữ liệu StockOut
        List<StockOut> stockOuts = stockOutRepository.findByDateBetween(startDate, endDate);
        List<InOutReportDTO> outReport = stockOuts.stream().map(stockOut -> {
            InOutReportDTO dto = new InOutReportDTO();
            dto.setId(stockOut.getId());
            dto.setProductId(stockOut.getProduct().getId());
            dto.setQuantity(stockOut.getQuantity());
            dto.setDate(stockOut.getDate());
            dto.setReason(stockOut.getReason());
            dto.setNote(stockOut.getNote());
            dto.setType("STOCK_OUT");
            return dto;
        }).collect(Collectors.toList());

        // Gộp danh sách StockIn và StockOut
        List<InOutReportDTO> combinedReport = new ArrayList<>();
        combinedReport.addAll(inReport);
        combinedReport.addAll(outReport);

        // Sắp xếp theo ngày (mới nhất trước)
        return combinedReport.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
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
