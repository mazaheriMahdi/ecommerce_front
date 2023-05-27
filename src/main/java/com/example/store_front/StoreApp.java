package com.example.store_front;

import com.example.store_front.Components.*;
import com.example.store_front.Service.ProductService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApp extends Application {
    @Override
    public void start(Stage stage) {

        // get screen size
        double screenHeight = Screen.getPrimary().getBounds().getHeight() - 100;
        double screenWeight = Screen.getPrimary().getBounds().getWidth() - 50;
        // main structure
        BorderPane borderPane = new BorderPane();
        NavBar navBar = new NavBar();
        borderPane.setTop(navBar);

        borderPane.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        borderPane.getStyleClass().add("darkPrimaryBack");


        ProductContainer productContainer = new ProductContainer();
        VBox vBox = new VBox();
        vBox.getChildren().add(new ProductContainerHeader());
        vBox.getChildren().add(productContainer);
        vBox.getChildren().add(new PaginationToolbar());
        vBox.setPadding(new Insets(10));
        vBox.getStyleClass().add("darkPrimaryBack");

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setBorder(Border.EMPTY);
        scrollPane.setBackground(Background.EMPTY);
        borderPane.setCenter(scrollPane);
        Scene mainScene = new Scene(borderPane, screenWeight, screenHeight);
        mainScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(mainScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}