package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product addClient(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteClient(long id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(long id, Product newProduct) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setSize(newProduct.getSize());
        product.setAvailable(newProduct.isAvailable());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductStock(long id, boolean updatingStock) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setAvailable(updatingStock);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByAvailable(boolean availability) {
        return productRepository.findByAvailable(availability);
    }
}
