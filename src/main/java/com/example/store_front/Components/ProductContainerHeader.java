package com.example.store_front.Components;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class ProductContainerHeader extends HBox {
    public ProductContainerHeader() {
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("darkPrimaryBack");
        MenuButton menuButton = new MenuButton();
        menuButton.getStyleClass().add("darkAccent");
        menuButton.setText("Sort By");
        menuButton.setPadding(new Insets(5));

        MenuButton menuButtonCategory = new MenuButton();
        menuButtonCategory.getStyleClass().add("darkAccent");
        menuButtonCategory.setText("Category");
        menuButtonCategory.setPadding(new Insets(5));

        MenuItem menuItem = new MenuItem();

        menuButton.getItems().add(menuItem);
        this.getChildren().addAll(menuButton , menuButtonCategory);
        this.setPadding(new Insets(5));
        this.setSpacing(15);


    }
}
