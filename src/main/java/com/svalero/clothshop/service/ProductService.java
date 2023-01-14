package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(long id) throws ProductNotFoundException;

    Product addProduct(Product product);

    void deleteProduct(long id) throws ProductNotFoundException;

    Product updateProduct(long id, Product updateProduct) throws ProductNotFoundException;
    Product updateProductStock(long id, boolean updateStock) throws ProductNotFoundException;

    List<Product> findByAvailable(boolean availability) throws ProductNotFoundException;


}
