package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.dto.CategoryDTO;
import com.warehouse.warehouse_backend.entity.Category;
import com.warehouse.warehouse_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy danh sách tất cả danh mục
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream().map(category -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    // Lấy danh sách sản phẩm thuộc một danh mục
    public List<ProductDTO> getStockByCategory(Long categoryId) {
        // Tìm danh mục theo ID, ném ngoại lệ nếu không tìm thấy
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Chuyển danh sách sản phẩm sang ProductDTO
        return category.getProducts().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setSku(product.getSku());
            dto.setUnit(product.getUnit());
            dto.setQuantity(product.getQuantity());
            dto.setPrice(product.getPrice());
            dto.setCategoryId(categoryId);
            return dto;
        }).collect(Collectors.toList());
    }
}
