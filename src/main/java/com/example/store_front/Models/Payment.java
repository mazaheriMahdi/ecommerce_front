package com.example.store_front.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    Status status;
    double amount;
    String cardNumber;
    LocalDate localDate ;
}
