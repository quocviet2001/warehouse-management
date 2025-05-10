package com.warehouse.warehouse_backend.service;

import com.warehouse.warehouse_backend.dto.ProductDTO;
import com.warehouse.warehouse_backend.entity.Category;
import com.warehouse.warehouse_backend.entity.Product;
import com.warehouse.warehouse_backend.repository.CategoryRepository;
import com.warehouse.warehouse_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setSku(productDTO.getSku());
        product.setUnit(productDTO.getUnit());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        product = productRepository.save(product);

        productDTO.setId(product.getId());
        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDTO.getName());
        product.setSku(productDTO.getSku());
        product.setUnit(productDTO.getUnit());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        productRepository.save(product);
        productDTO.setId(id);
        return productDTO;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public List<ProductDTO> getProducts(String name, String sku) {
        List<Product> products;
        if (name != null && !name.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(name);
        } else if (sku != null && !sku.isEmpty()) {
            products = productRepository.findBySkuContainingIgnoreCase(sku);
        } else {
            products = productRepository.findAll();
        }

        return products.stream().map(product -> {
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
