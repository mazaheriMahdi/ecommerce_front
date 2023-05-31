package com.example.store_front;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Service.Cart.AddToCartEvent;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Review.ReviewService;
import com.example.store_front.Service.User.UserService;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

public class EventHandler {
    public EventHandler() {
        CustomAlert customAlert = new CustomAlert("Product Added to Cart");
        CartService.addListener(customAlert::show);
        ReviewService.addListener(()->{
            CustomAlert reviewAlert = new CustomAlert("Review Added");
            reviewAlert.show();
        });

        UserService.addOnUserLoginListener(()->{
            CustomAlert loginAlert = new CustomAlert("Login Successful");
            loginAlert.show();
        });
    }
}
