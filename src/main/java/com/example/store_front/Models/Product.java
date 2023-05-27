package com.example.store_front.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    Long id;
    private String name;
    private double count;
    private double price;
    private double averagePoint;
    private Category category;
    private String image;

    private List<Map<String , String>> productProperties;


}
