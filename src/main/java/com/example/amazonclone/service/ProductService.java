package com.example.amazonclone.service;

import com.example.amazonclone.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<ProductDTO> mockProducts = new ArrayList<>();

    public List<ProductDTO> getAllProducts() {
        return mockProducts;
    }

    public ProductDTO addProduct(ProductDTO product) {
        mockProducts.add(product);
        return product;
    }
}
