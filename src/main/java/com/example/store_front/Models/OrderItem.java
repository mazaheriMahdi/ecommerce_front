package com.example.store_front.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderItem {
    private Long productId;
    private int quantity;
    private double totalPrice;
}
