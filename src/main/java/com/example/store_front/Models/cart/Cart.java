package com.example.store_front.Models.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@ToString
public class Cart implements Serializable {
    private LocalDate placed_at;
    private List<CartItem> cartItems;
    private String discount;


    public double getTotalPrice(){
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }
}
