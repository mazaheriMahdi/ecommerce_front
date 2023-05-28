package com.example.store_front.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Flow;

import com.example.store_front.Models.AddToCartRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.store_front.Constant.CART_API_END_POINT;
import static com.example.store_front.Constant.PRODUCT_API_END_POINT;

public class CartController {
    static HttpRequest httpRequest;

    public CartController() {

    }
    public  static String getCartId() throws IOException, InterruptedException {

        httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(CART_API_END_POINT))
                .header("Authorization" , "9e848dfa-bb34-4b69-9e52-476a4280c7b6")
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Map<String , String> data = new Gson().fromJson(response.body(), new TypeToken<Map<String, String>>() {
        }.getType());

        return data.get("cartId");
    }
    static public void addToCart(Long productId , int quantity ) throws IOException, InterruptedException {
        String cartId = getCartId();
        URI uri = URI.create(CART_API_END_POINT + "/" + cartId + "/items");
        AddToCartRequestModel model = new AddToCartRequestModel(productId  , quantity);
        String object = new Gson().toJson(model);
        HttpRequest httpRequest2 = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(object))
                .header("Authorization" , "9e848dfa-bb34-4b69-9e52-476a4280c7b6")
                .header("Content-Type" , "application/json")
                .uri(uri).build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
