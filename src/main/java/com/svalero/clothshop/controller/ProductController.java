package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.exception.ErrorMessage;
import com.svalero.clothshop.exception.ProductNotFoundException;
import com.svalero.clothshop.service.ClientService;
import com.svalero.clothshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "available", defaultValue = "")
                                                         String available) throws ProductNotFoundException {
        if (available.equals("")) {
            logger.info("Lista de productos");
            return ResponseEntity.status(200).body(productService.findAll());
        }
        logger.info("Filtrado por disponibilidad");
        return ResponseEntity.status(200).body(productService.findByAvailable(Boolean.parseBoolean(available)));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductId(@PathVariable long id) throws ProductNotFoundException{
        Product product = productService.findById(id);
        logger.info("Búsqueda del producto por id");
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        logger.info("Producto añadido");
        return ResponseEntity.status(201).body(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable long id) throws ProductNotFoundException{
        productService.deleteProduct(id);
        logger.info("Producto eliminado");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,
                                                 @RequestBody Product product) throws ProductNotFoundException{
        Product newProduct = productService.updateProduct(id,product);
        logger.info("Producto actualizado");
        return ResponseEntity.status(200).body(newProduct);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> updateProductStock(@PathVariable long id,
                                                      @RequestBody Product product) throws ProductNotFoundException{
        Product updateStock = productService.updateProductStock(id, product.isAvailable());
        logger.info("Stock actualizado");
        return ResponseEntity.status(200).body(updateStock);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException pnfe){
        logger.error(pnfe.getMessage(),pnfe);
        ErrorMessage notfound = new ErrorMessage(404,pnfe.getMessage());
        return new ResponseEntity<>(notfound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> badRequestException(MethodArgumentNotValidException manve){

        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname, message);
        });

        logger.error(manve.getMessage(),manve);
        ErrorMessage badRequest = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error(e.getMessage(),e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
