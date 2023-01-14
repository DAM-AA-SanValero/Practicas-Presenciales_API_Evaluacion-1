package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Product;
import com.svalero.clothshop.exception.ProductNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByAvailable(boolean available) throws ProductNotFoundException;

}
