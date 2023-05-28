package com.example.store_front.Page;

import com.example.store_front.Components.NavBar;
import com.example.store_front.Components.PaginationToolbar;
import com.example.store_front.Components.ProductContainer;
import com.example.store_front.Components.ProductContainerHeader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainPage  extends BorderPane {


    public MainPage() {
        super();
        NavBar navBar = new NavBar();
        this.setTop(navBar);
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        ProductContainer productContainer = new ProductContainer();
        VBox vBox = new VBox();
        vBox.getChildren().add(new ProductContainerHeader());
        vBox.getChildren().add(productContainer);
        vBox.getChildren().add(new PaginationToolbar(productContainer));
        vBox.setPadding(new Insets(10));
        vBox.getStyleClass().add("darkPrimaryBack");
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setBorder(Border.EMPTY);
        scrollPane.setBackground(Background.EMPTY);
        this.setCenter(scrollPane);
    }
}
