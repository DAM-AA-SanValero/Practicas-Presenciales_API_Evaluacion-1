package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService{

    @Autowired
    DiscountRepository discountRepository;
    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount findById(long id) {
        return discountRepository.findById(id).orElseThrow();
    }

    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(long id) {
        Discount discount = discountRepository.findById(id).orElseThrow();
        discountRepository.delete(discount);
    }

    @Override
    public Discount updateDiscount(long id, Discount newDiscount) {
        Discount discount = discountRepository.findById(id).orElseThrow();
        discount.setEvent(newDiscount.getEvent());
        discount.setDiscountDate(newDiscount.getDiscountDate());
        discount.setDiscountedPrice(newDiscount.getDiscountedPrice());

        return discountRepository.save(discount);
    }

    @Override
    public Discount updateDiscountPrice(long id, float newPrice) {
        Discount discount = discountRepository.findById(id).orElseThrow();
        discount.setDiscountedPrice(newPrice);
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> findByEvent(String event) {
        return discountRepository.findByEvent(event);
    }
}
