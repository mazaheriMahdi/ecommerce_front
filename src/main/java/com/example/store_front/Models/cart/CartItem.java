package com.example.store_front.Models.cart;


import com.example.store_front.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class CartItem implements Serializable {
    private Product product;
    private int quantity;
    private double totalPrice;
}
