package com.example.store_front;
import com.example.store_front.Router.Router;
import javafx.application.Application;
import javafx.stage.Stage;
public class StoreApp extends Application {
    @Override
    public void start(Stage stage) {
        EventHandler eventHandler = new EventHandler();
        Router.toMainPage();
    }

    public static void main(String[] args) {
        launch();
    }
}