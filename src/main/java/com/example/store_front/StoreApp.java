package com.example.store_front;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.User.UserService;
import javafx.application.Application;
import javafx.stage.Stage;

public class StoreApp extends Application {
    @Override
    public void start(Stage stage) {
        Router.toMainPage();
        EventHandler eventHandler = new EventHandler();
        if (UserService.checkDataFileExistence()) {
            UserService.readFromFile();
        }
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            CustomAlert customAlert = new CustomAlert("Error");
            customAlert.show();
        });




//        Router.toAdminPage();
    }

    public static void main(String[] args) {
            launch();



    }
}