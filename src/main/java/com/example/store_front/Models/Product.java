package com.example.store_front.Models;


import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
