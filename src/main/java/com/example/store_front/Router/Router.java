package com.example.store_front.Router;

import com.example.store_front.Models.Product;
import com.example.store_front.Page.*;
import com.example.store_front.Page.Admin.AdminPage;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Order.OrderService;
import com.example.store_front.Service.Review.ReviewService;
import com.example.store_front.Service.User.UserService;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Router {
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


    static void runEvent() {
        for (RoutEvent routEvent : listener) {
            routEvent.onRoutEvent();
        }
    }

    public static void toProductPage(Product product) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Scene scene = null;
                try {
                    scene = new Scene(new SingleProductPage(product, ReviewService.getReviews(product.getId())), screenWeight, screenHeight);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }

    public static void toLoginPage() {
        Stage stage = new Stage();
        stage.setScene(new Scene(new LoginPage(), screenWeight, screenHeight));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void toCartPage() {
        Stage stage = new Stage();
        try {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(new CartPage(CartService.getCart()), 500, 500));
            stage.show();

            CartService.addOnCartItemDeleteListener(()->{
                try {
                    stage.setScene(new Scene(new CartPage(CartService.getCart()), 500, 500));
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            OrderService.addOrderCreatedEventListener(()->{
                try {
                    stage.setScene(new Scene(new CartPage(CartService.getCart()), 500, 500));
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            OrderService.addCreditNotEnoughEventListener(()->{
                try {
                    stage.setScene(new Scene(new CartPage(CartService.getCart()), 500, 500));
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            CartService.addOnDiscountSetListener(()->{
                try {
                    stage.setScene(new Scene(new CartPage(CartService.getCart()), 500, 500));
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void toPaymentPage(){
        Stage stage = new Stage();
        stage.setScene(new Scene(new PaymentPage(), 450, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void toHistoryPage(){
        Stage stage = new Stage();
        stage.setScene(new Scene(new HistoryPage(), 600, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public static void toMainPage() {
        Scene scene = new Scene(new MainPage(), screenWeight, screenHeight);
        System.out.println("running");
        scene.getStylesheets().add(Router.class.getResource("/style.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        UserService.addOnStaffLoginListener(stage::close);
        UserService.addOnUserLogoutListener(stage::close);
        runEvent();
    }

    public static void toProfilePage() {
        Stage stage = new Stage();
        stage.setScene(new Scene(new ProfilePage(), 500, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public static void toAdminPage() {
        Stage stage = new Stage();
        stage.setScene(new Scene(new AdminPage(), screenWeight, screenHeight));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        UserService.addOnUserLogoutListener(()->stage.close());
    }

    public static void toSignInPage() {
        Stage stage = new Stage();
        stage.setScene(new Scene(new SignInPage(), 500, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
