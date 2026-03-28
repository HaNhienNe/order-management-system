package com.sangdd.omsbe.controller;

import com.sangdd.omsbe.entity.Product;
import com.sangdd.omsbe.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController (ProductService service) {
        this.service = service;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }
}
