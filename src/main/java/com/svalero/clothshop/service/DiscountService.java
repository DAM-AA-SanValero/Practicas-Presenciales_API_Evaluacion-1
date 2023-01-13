package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> findAll();
    Discount findById(long id);

    Discount addDiscount(Discount discount);

    void deleteDiscount(long id);
    Discount updateDiscount(long id, Discount discount);

    Discount updateDiscountPrice(long id, float newPrice);

    List<Discount> findByEvent(String event);

}
