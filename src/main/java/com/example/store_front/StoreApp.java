package com.example.store_front;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Exception.LoginFailedException;
import com.example.store_front.Models.Order;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.Order.OrderService;
import com.example.store_front.Service.User.UserService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApp extends Application {
    @Override
    public void start(Stage stage) {
        if (UserService.checkDataFileExistence()) {
            UserService.readFromFile();
        }
        EventHandler eventHandler = new EventHandler();
        Router.toMainPage();
        try {
            System.out.println(OrderService.getAcceptedOrders());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
            launch();



    }
}