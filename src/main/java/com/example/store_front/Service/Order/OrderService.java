package com.example.store_front.Service.Order;

import com.example.store_front.Models.Order;
import com.example.store_front.Service.Cart.LocaleDateAdapter;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.store_front.Constant.CART_API_END_POINT;
import static com.example.store_front.Constant.ORDER_API_END_POINT;
import static com.example.store_front.Service.Cart.CartService.getCartId;

public class OrderService {
    private static List<CreditNotEnoughEvent> creditNotEnoughEventListeners;
    private static  List<LackOfInventoryEvent> lackOfInventoryEventListeners;
    private static List<OrderCreatedEvent>  orderCreatedEventListeners;
    static {
        creditNotEnoughEventListeners = new ArrayList<>();
        lackOfInventoryEventListeners = new ArrayList<>();
        orderCreatedEventListeners = new ArrayList<>();
    }
    public static void addCreditNotEnoughEventListener(CreditNotEnoughEvent listener){
        creditNotEnoughEventListeners.add(listener);
    }
    public static void addLackOfInventoryEventListener(LackOfInventoryEvent listener){
        lackOfInventoryEventListeners.add(listener);
    }
    public static void addOrderCreatedEventListener(OrderCreatedEvent listener){
        orderCreatedEventListeners.add(listener);
    }



    public static void createOrders(String cartId) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(ORDER_API_END_POINT +"/" + cartId))
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (httpResponse.statusCode() == 200) {
            for (OrderCreatedEvent listener : orderCreatedEventListeners) {
                listener.onOrderCreated();
            }
        }else if(httpResponse.statusCode() == 403){
            for (CreditNotEnoughEvent listener : creditNotEnoughEventListeners) {
                listener.OnCreditNotEnoughReceived();
            }
        }
        else {
            for (LackOfInventoryEvent listener : lackOfInventoryEventListeners) {
                listener.onLackOfInventoryReceived();
            }
        }
    }


    public static List<Order> getAcceptedOrders() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", UserService.getAuthToken())
                .uri(URI.create(ORDER_API_END_POINT +"/accepted"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class , new LocaleDateAdapter())
                .create();

        List<Order> orders =gson.fromJson(response.body() , new TypeToken<List<Order>>(){}.getType());
        return orders;
    }
}
