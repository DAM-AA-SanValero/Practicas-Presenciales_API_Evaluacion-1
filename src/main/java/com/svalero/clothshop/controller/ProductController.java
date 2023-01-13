package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.service.ClientService;
import com.svalero.clothshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "available", defaultValue = "") String available) {
        if (available.equals("")) {
            return ResponseEntity.status(200).body(productService.findAll());
        }
        return ResponseEntity.status(200).body(productService.findByAvailable(Boolean.parseBoolean(available)));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductId(@PathVariable long id){
        Product product = productService.findById(id);
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(201).body(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
        Product newProduct = productService.updateProduct(id,product);
        return ResponseEntity.status(200).body(newProduct);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> updateProductStock(@PathVariable long id, @RequestBody Product product){
        Product updateStock = productService.updateProductStock(id, product.isAvailable());
        return ResponseEntity.status(200).body(updateStock);
    }
}
