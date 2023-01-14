package com.svalero.clothshop.controller;

import com.svalero.clothshop.domain.Client;
import com.svalero.clothshop.domain.Discount;
import com.svalero.clothshop.exception.DiscountNotFoundException;
import com.svalero.clothshop.exception.ErrorMessage;
import com.svalero.clothshop.service.ClientService;
import com.svalero.clothshop.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DiscountController {

    private final Logger logger = LoggerFactory.getLogger(DiscountController.class);
    @Autowired
    DiscountService discountService;

    @GetMapping("/discounts")
    public ResponseEntity<List<Discount>> getDiscounts(@RequestParam(name = "event", defaultValue = "")
                                                           String event) throws DiscountNotFoundException {
        if (event.equals("")) {
            logger.info("Lista de descuentos");
            return ResponseEntity.status(200).body(discountService.findAll());
        }
        logger.info("Filtrado por evento");
        return ResponseEntity.status(200).body(discountService.findByEvent(event));
    }

    @GetMapping("/discounts/{id}")
    public ResponseEntity<Discount> getDiscountId(@PathVariable long id) throws DiscountNotFoundException{
        Discount discount = discountService.findById(id);
        logger.info("Búsqueda por id del descuento");
        return ResponseEntity.status(200).body(discount);
    }

    @PostMapping("/discounts")
    public ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        Discount newDiscount = discountService.addDiscount(discount);
        logger.info("Descuento añadido");
        return ResponseEntity.status(201).body(newDiscount);
    }

    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable long id) throws DiscountNotFoundException{
        discountService.deleteDiscount(id);
        logger.info("Descuento eliminado");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/discounts/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable long id,
                                                   @RequestBody Discount discount) throws DiscountNotFoundException{
        Discount newDiscount = discountService.updateDiscount(id,discount);
        logger.info("Descuento actualizado");
        return ResponseEntity.status(200).body(newDiscount);
    }

    @PatchMapping("/discounts/{id}")
    public ResponseEntity<Discount> updateDiscountPrice(@PathVariable long id,
                                                        @RequestBody Discount discount) throws DiscountNotFoundException{
        Discount updateDiscountPrice = discountService.updateDiscountPrice(id, discount.getDiscountedPrice());
        logger.info("Precio de descuento actualizado");
        return ResponseEntity.status(200).body(updateDiscountPrice);
    }

    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<ErrorMessage> discountNotFoundException(DiscountNotFoundException dnfe){
        logger.error(dnfe.getMessage(),dnfe);
        ErrorMessage notfound = new ErrorMessage(404,dnfe.getMessage());
        return new ResponseEntity<>(notfound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> badRequestException(MethodArgumentNotValidException manve){

        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname, message);
        });

        logger.error(manve.getMessage(),manve);
        ErrorMessage badRequest = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error(e.getMessage(),e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
