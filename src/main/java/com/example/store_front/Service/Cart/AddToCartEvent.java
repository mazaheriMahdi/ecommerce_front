package com.example.store_front.Service.Cart;

import java.io.IOException;

public interface AddToCartEvent {
    void onAddToCart() throws IOException, InterruptedException;
}
