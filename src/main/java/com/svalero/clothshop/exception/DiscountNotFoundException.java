package com.svalero.clothshop.exception;

public class DiscountNotFoundException extends Exception{

    public DiscountNotFoundException() {
        super("Discount not found");
    }
    public DiscountNotFoundException(String message){
        super(message);
    }

}
