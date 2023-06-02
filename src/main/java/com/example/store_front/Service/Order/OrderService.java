package com.example.store_front.Service.Order;

import com.example.store_front.Service.User.UserService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
}
