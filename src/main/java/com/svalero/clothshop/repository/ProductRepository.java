package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductRepository, Long> {

    List<ProductRepository> findall();

    List<Product> findByAvailable(boolean available);

}
