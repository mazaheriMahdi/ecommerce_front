package com.example.store_front.Models.RequestModel;

import com.example.store_front.Models.Category;


public record ProductRequestModel(String name, double count, double price, double averagePoint, Category category, String image) {
}
