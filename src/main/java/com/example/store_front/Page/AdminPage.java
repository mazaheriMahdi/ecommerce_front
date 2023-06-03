package com.example.store_front.Page;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.skin.TabPaneSkin;

public class AdminPage extends TabPane {
    public AdminPage() {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        Tab products = new Tab("Products");
        products.setContent(new ProductCreateTab());
        Tab orders = new Tab("Orders");
        Tab categories = new Tab("Categories");
        Tab discounts = new Tab("Discounts");
        Tab users = new Tab("Users");
        Tab profile = new Tab("Profile");
        this.getTabs().addAll(products,discounts,categories, orders, users, profile);


        products.setClosable(false);
        orders.setClosable(false);
        users.setClosable(false);
        profile.setClosable(false);


    }
}
