package com.warehouse.warehouse_backend.controller;

import com.warehouse.warehouse_backend.dto.CategoryDTO;
import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}/stock")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<ProductDTO>> getStockByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getStockByCategory(id));
    }
}
