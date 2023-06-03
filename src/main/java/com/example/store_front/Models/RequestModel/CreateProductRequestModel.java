package com.example.store_front.Models.RequestModel;

import java.util.List;
import java.util.Map;

public record CreateProductRequestModel(ProductRequestModel product , List<Map<String, String>> productProperty) {
}