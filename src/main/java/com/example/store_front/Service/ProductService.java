package com.example.store_front.Service;

import com.example.store_front.Models.Category;
import com.example.store_front.Models.Product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.example.store_front.Constant.PRODUCT_API_END_POINT;

public class ProductService {
    HttpRequest httpRequest;


    public ProductService() {
        this.httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(PRODUCT_API_END_POINT))
                .header("Authorization" , "9e848dfa-bb34-4b69-9e52-476a4280c7b6")
                .build();
    }

    public List<Product> getProducts() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(this.httpRequest, HttpResponse.BodyHandlers.ofString());

        Map<String , Object> data = new Gson().fromJson(response.body(), new TypeToken<Map<String, Object>>() {
        }.getType());
        Map<String , Object> fullProducts = new Gson().fromJson(new Gson().toJson(data.get("_embedded")), new TypeToken<Map<String, Object>>() {
        }.getType());
        List<Map<String , Object>> productsMap = new Gson().fromJson(new Gson().toJson(fullProducts.get("fullProducts")) , new TypeToken<List<Map<String, Object>>>() {
        }.getType());

        List<Product> products = new ArrayList<>();

        for (Map<String , Object> productMap : productsMap) {
            Product product = new Product();

            product.setName(productMap.get("name").toString());
            product.setCount(Double.parseDouble(productMap.get("count").toString()));
            product.setPrice(Double.parseDouble(productMap.get("price").toString()));
            System.out.println("-------------------");
            product.setAveragePoint(Double.parseDouble(productMap.get("averagePoint").toString()));
            System.out.println("-------------------");
            product.setImage( productMap.get("image").toString());
            product.setCategory(new Category( ((productMap.get("category")).toString())));

            List<Map<String , String>> productProperties = new Gson().fromJson(new Gson().toJson(productMap.get("productProperties")) , new TypeToken<List<Map<String, Object>>>() {
            }.getType());
            product.setProductProperties(productProperties);
            products.add(product);
        }
        System.out.println(products);

        return products;

    }
}
