package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.product.RegisterProductRequestDTO;
import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity register (@RequestBody RegisterProductRequestDTO body) throws Exception{
        productService.registerProduct(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
