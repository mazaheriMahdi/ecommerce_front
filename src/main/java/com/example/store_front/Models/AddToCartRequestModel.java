package com.example.store_front.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AddToCartRequestModel {
    public Long productId;
    public int quantity;

}
