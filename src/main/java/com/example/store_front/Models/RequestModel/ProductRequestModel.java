package com.example.store_front.Models.RequestModel;

import com.example.store_front.Models.Category;
import lombok.*;

import java.util.List;
import java.util.Map;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestModel {
    private String name;
    private double count;
    private double price;
    private double averagePoint;
    private Category category;
    private String image;

}
