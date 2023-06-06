package com.example.store_front.Service.Category;

import com.example.store_front.Models.Category;
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

import static com.example.store_front.Constant.CATEGORY_API_END_POINT;

public class CategoryService {
    private static List<CategoryAddEvent> categoryAddEvents;
    static {
        categoryAddEvents = new ArrayList<>();
    }
    public static List<Category> getAll() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CATEGORY_API_END_POINT))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.printf(response.body());
        List<Category> categories = new Gson().fromJson(response.body(), new TypeToken<List<Category>>() {
        }.getType());
        return categories;
    }

    public static void addCategoryAddEvent(CategoryAddEvent categoryAddEvent) {
        categoryAddEvents.add(categoryAddEvent);
    }
    public static void runCategoryAddEvent() {
        for (CategoryAddEvent categoryAddEvent : categoryAddEvents) {
            categoryAddEvent.addCategory();
        }
    }
    public static void addCategory(String name) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + name + "\"}"))
                .uri(URI.create(CATEGORY_API_END_POINT))
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.printf(response.body());
        runCategoryAddEvent();

    }
}
