package com.example.store_front;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Models.User;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Category.CategoryService;
import com.example.store_front.Service.Discount.DiscountService;
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

        UserService.addOnStaffLoginListener(() -> {
            System.out.println("asdasdasdasdasdasdasda");
            CustomAlert staffLoginAlert = new CustomAlert("Staff Login Successful");
            staffLoginAlert.show();
            Router.toAdminPage();
        });

        UserService.addOnLoginNeededSend(() -> {
            CustomAlert loginNeededAlert = new CustomAlert("Login Needed");
            loginNeededAlert.show();
        });

        DiscountService.addDiscountAddedListener(() -> {
            CustomAlert discountAddedAlert = new CustomAlert("Discount Added");
            discountAddedAlert.show();
        });

        CategoryService.addCategoryAddEvent(() -> {
            CustomAlert categoryAddedAlert = new CustomAlert("Category Added");
            categoryAddedAlert.show();
        });

        UserService.addOnUserLogoutListener(()->{
            Router.toMainPage();
        });

    }
}
