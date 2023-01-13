package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByAvailable(boolean available);

}
