package com.example.store_front.Exception;

public class AddToCartException extends RuntimeException{
    public AddToCartException() {
        super("Add to cart failed");
    }
}
