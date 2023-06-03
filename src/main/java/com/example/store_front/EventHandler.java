package com.example.store_front;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Order.OrderService;
import com.example.store_front.Service.Review.ReviewService;
import com.example.store_front.Service.User.UserService;

public class EventHandler {
    public EventHandler() {
        CustomAlert customAlert = new CustomAlert("Product Added to Cart");
        CartService.addListener(customAlert::show);
        ReviewService.addListener(() -> {
            CustomAlert reviewAlert = new CustomAlert("Review Added");
            reviewAlert.show();
        });

        UserService.addOnUserLoginListener(() -> {
            CustomAlert loginAlert = new CustomAlert("Login Successful");
            loginAlert.show();
        });

        OrderService.addOrderCreatedEventListener(() -> {
            CustomAlert orderAlert = new CustomAlert("Order Created");
            orderAlert.show();
        });

        OrderService.addCreditNotEnoughEventListener(() -> {
            CustomAlert creditAlert = new CustomAlert("Credit Not Enough");
            creditAlert.show();
        });

        OrderService.addLackOfInventoryEventListener(() -> {
            CustomAlert inventoryAlert = new CustomAlert("Lack Of Inventory");
            inventoryAlert.show();
        });

        UserService.addOnProfileUpdateListener(() -> {
            CustomAlert profileAlert = new CustomAlert("Profile Updated");
            profileAlert.show();
            UserService.setCurrentUser();
        });

    }
}
