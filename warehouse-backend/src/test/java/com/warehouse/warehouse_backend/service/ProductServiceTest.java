package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.entity.Category;
import com.warehouse.warehouse_backend.entity.Product;
import com.warehouse.warehouse_backend.repository.CategoryRepository;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        // Chuẩn bị dữ liệu
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setSku("SKU123");
        productDTO.setUnit("pcs");
        productDTO.setQuantity(100);
        productDTO.setPrice(10.0);
        productDTO.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setSku("SKU123");
        product.setUnit("pcs");
        product.setQuantity(100);
        product.setPrice(10.0);
        product.setCategory(category);

        // Mock repository
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Thực hiện test
        ProductDTO result = productService.createProduct(productDTO);

        // Kiểm tra kết quả
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getName());
        assertEquals("SKU123", result.getSku());
    }
}

