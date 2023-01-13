package com.svalero.clothshop.repository;

import com.svalero.clothshop.domain.Discount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

    List<Discount> findAll();

    List<Discount> findByEvent(String event);

}
