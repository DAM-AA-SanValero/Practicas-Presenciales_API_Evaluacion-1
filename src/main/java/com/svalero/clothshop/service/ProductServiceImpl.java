package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.exception.ProductNotFoundException;
import com.svalero.clothshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(long id, Product newProduct) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setSize(newProduct.getSize());
        product.setAvailable(newProduct.isAvailable());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductStock(long id, boolean updatingStock) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setAvailable(updatingStock);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByAvailable(boolean availability) throws ProductNotFoundException{
        return productRepository.findByAvailable(availability);
    }
}
