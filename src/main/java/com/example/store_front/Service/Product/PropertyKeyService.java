package com.example.store_front.Service.Product;


import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.example.store_front.Constant.PROPERTY_KEY_API_END_POINT;

public class PropertyKeyService {

    public static List<Map<String , String>> getAll() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Content-Type", "application/json")
                .header("Authorization", UserService.getAuthToken())
                .uri(URI.create(PROPERTY_KEY_API_END_POINT))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Map<String , String>> data = new Gson().fromJson(httpResponse.body() , new TypeToken<List<Map<String , String>>>(){}.getType());
        return data;
    }

}

