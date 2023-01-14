package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.exception.DiscountNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    List<Discount> findAll();

    List<Discount> findByEvent(String event) throws DiscountNotFoundException;

}
