package com.example.store_front.Service.Cart;


import com.example.store_front.Models.RequestModel.AddToCartRequestModel;
import com.example.store_front.Models.cart.Cart;
import com.example.store_front.Models.cart.CartItem;
import com.example.store_front.Service.User.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

public class CartService {
    public static List<AddToCartEvent> listeners;
    public static List<OnCartItemRemoveEvent> onCartItemRemoveEventListeners;
    private static Cart currentCart;
    private static String cartId;

    static {
        listeners = new ArrayList<>();
        onCartItemRemoveEventListeners = new ArrayList<>();
    }

    public CartService() {
    }

    public static void setCartId(String cartId) {
        CartService.cartId = cartId;
    }

    public static Cart getCart() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CART_API_END_POINT +"/" + getCartId()))
                .header("Authorization", UserService.getAuthToken())
                .header("Content-Type", "application/json")
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocaleDateAdapter())
                .create();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(httpResponse.body(), Cart.class);
    }

    public static int getCartItemsCount() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CART_API_END_POINT +"/" + getCartId() + "/items/count"))
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String , String> data = new Gson().fromJson(response.body(), new TypeToken<Map<String, String>>(){}.getType());
        return Integer.parseInt(data.get("count"));
    }

    public static String getCartId() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(CART_API_END_POINT))
                .header("Authorization", UserService.getAuthToken())
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Map<String, String> data = new Gson().fromJson(response.body(), new TypeToken<Map<String, String>>() {
        }.getType());
        setCartId(data.get("cartId"));
        return data.get("cartId");
    }

    static public void addToCart(Long productId, int quantity) throws IOException, InterruptedException {
        String cartId = getCartId();
        URI uri = URI.create(CART_API_END_POINT + "/" + cartId + "/items");
        AddToCartRequestModel model = new AddToCartRequestModel(productId, quantity);
        String object = new Gson().toJson(model);
        HttpRequest httpRequest2 = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(object))
                .header("Authorization", UserService.getAuthToken())
                .header("Content-Type", "application/json")
                .uri(uri).build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        for (AddToCartEvent listener : listeners) {
            listener.onAddToCart();
        }
    }

    public static void deleteCartItem(CartItem cartItem) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(CART_API_END_POINT + "/" + getCartId() + "/items/" + cartItem.getProduct().getId()))
                .header("Authorization", UserService.getAuthToken())
                .header("Content-Type", "application/json")
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        for (OnCartItemRemoveEvent listener : onCartItemRemoveEventListeners) {
            listener.onCartItemRemove();
        }
    }

    public static void addOnCartItemDeleteListener(OnCartItemRemoveEvent listener) {
        onCartItemRemoveEventListeners.add(listener);
    }

    public static void addListener(AddToCartEvent listener) {
        listeners.add(listener);
    }
}
