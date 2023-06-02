package com.example.store_front.Models.RequestModel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PaymentRequestModel {
    String cardNumber;
    String cvv2;
    double amount;
}

