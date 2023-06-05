package com.example.store_front.Service.Discount;

import com.example.store_front.Models.Discount;
import com.example.store_front.Models.Payment;
import com.example.store_front.Models.RequestModel.DiscountRequestModel;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.store_front.Constant.DISCOUNT_API_END_POINT;

public class DiscountService {
    private static List<DiscountAddedEvent> discountAddedEventList;
    static {
        discountAddedEventList = new ArrayList<>();
    }



    public static void addDiscountAddedListener(DiscountAddedEvent event) {
        discountAddedEventList.add(event);
    }
    private static void runDiscountAddedEvent() {
        for (DiscountAddedEvent event : discountAddedEventList) {
            event.onDiscountAdded();
        }
    }

    public static List<Discount> getAllDiscounts() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DISCOUNT_API_END_POINT))
                .header("Content-Type", "application/json")
                .headers("Authorization", UserService.getAuthToken())
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
        System.out.printf("response.body() = %s\n", response.body());
        return new Gson().fromJson(response.body() , new TypeToken<List<Discount>>(){}.getType());
    }
    public static void addDiscount(int percent , int count , int day , String email) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DISCOUNT_API_END_POINT))
                .headers("Authorization", UserService.getAuthToken())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(new DiscountRequestModel(percent, count, day ,email) , DiscountRequestModel.class)))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request , HttpResponse.BodyHandlers.ofString());
        System.out.printf(response.body());
        runDiscountAddedEvent();
    }
}
