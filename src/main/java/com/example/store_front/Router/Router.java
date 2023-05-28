package com.example.store_front.Router;

import com.example.store_front.Components.ProductCard;
import com.example.store_front.Models.Product;
import com.example.store_front.Page.MainPage;
import com.example.store_front.Page.SingleProductPage;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Router {
    static Stage stage;
    static double screenHeight;
    static double screenWeight;
    static List<RoutEvent> listener;

    static List<Node> pages;


    static {
        screenHeight = Screen.getPrimary().getBounds().getHeight() - 100;
        screenWeight = Screen.getPrimary().getBounds().getWidth() - 50;
        listener = new ArrayList<>();
        pages = new ArrayList<>();
    }

    public static void setStage(Stage stage) {
        Router.stage = stage;
    }





    static void runEvent() {
        for (RoutEvent routEvent : listener) {
            routEvent.onRoutEvent();
        }
    }

    public static void toProductPage(Product product){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = new Scene(new SingleProductPage(product) , screenWeight , screenHeight);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
    public static void toMainPage() {
        Scene scene = new Scene(new MainPage(), screenWeight, screenHeight);
        System.out.println("running");
        scene.getStylesheets().add(Router.class.getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        runEvent();
    }

}
