package com.example.store_front.Models;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Long customerId;
    private LocalDate placedAt ;

    private List<OrderItem> orderItems;

    private double totalPrice;
}
