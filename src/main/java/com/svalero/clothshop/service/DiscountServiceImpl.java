package com.svalero.clothshop.service;

import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.exception.DiscountNotFoundException;
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
    public Discount findById(long id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(long id) throws DiscountNotFoundException {
        Discount discount = discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
        discountRepository.delete(discount);
    }

    @Override
    public Discount updateDiscount(long id, Discount newDiscount) throws DiscountNotFoundException {
        Discount discount = discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
        discount.setEvent(newDiscount.getEvent());
        discount.setDiscountDate(newDiscount.getDiscountDate());
        discount.setDiscountedPrice(newDiscount.getDiscountedPrice());

        return discountRepository.save(discount);
    }

    @Override
    public Discount updateDiscountPrice(long id, float newPrice) throws DiscountNotFoundException {
        Discount discount = discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
        discount.setDiscountedPrice(newPrice);
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> findByEvent(String event) throws DiscountNotFoundException {
        return discountRepository.findByEvent(event);
    }
}
