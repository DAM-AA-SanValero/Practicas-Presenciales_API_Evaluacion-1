package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.exception.DiscountNotFoundException;

import java.util.List;

public interface DiscountService {

    List<Discount> findAll();
    Discount findById(long id) throws DiscountNotFoundException;

    Discount addDiscount(Discount discount);

    void deleteDiscount(long id) throws DiscountNotFoundException;
    Discount updateDiscount(long id, Discount discount) throws DiscountNotFoundException;

    Discount updateDiscountPrice(long id, float newPrice) throws DiscountNotFoundException;

    List<Discount> findByEvent(String event) throws DiscountNotFoundException;

}
