package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.service.ClientService;
import com.svalero.clothshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping("/discounts")
    public ResponseEntity<List<Discount>> getDiscounts(@RequestParam(name = "event", defaultValue = "") String event) {
        if (event.equals("")) {
            return ResponseEntity.status(200).body(discountService.findAll());
        }
        return ResponseEntity.status(200).body(discountService.findByEvent(event));
    }

    @GetMapping("/discounts/{id}")
    public ResponseEntity<Discount> getDiscountId(@PathVariable long id){
        Discount discount = discountService.findById(id);
        return ResponseEntity.status(200).body(discount);
    }

    @PostMapping("/discounts")
    public ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        Discount newDiscount = discountService.addDiscount(discount);
        return ResponseEntity.status(201).body(newDiscount);
    }

    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable long id){
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/discounts/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable long id, @RequestBody Discount discount){
        Discount newDiscount = discountService.updateDiscount(id,discount);
        return ResponseEntity.status(200).body(newDiscount);
    }

    @PatchMapping("/discounts/{id}")
    public ResponseEntity<Discount> updateDiscountPrice(@PathVariable long id, @RequestBody Discount discount){
        Discount updateDiscountPrice = discountService.updateDiscountPrice(id, discount.getDiscountedPrice());
        return ResponseEntity.status(200).body(updateDiscountPrice);
    }
}
