package com.example.store_front.Page.Admin;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class AdminPage extends TabPane {
    public AdminPage() {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        Tab products = new Tab("Products");
        products.setContent(new ProductCreateTab());
        Tab orders = new Tab("Application");
        orders.setContent(new ApplicationTab());
        Tab categories = new Tab("Categories");
        categories.setContent(new CategoryTab());
        Tab discounts = new Tab("Discounts");
        discounts.setContent(new DiscountTabPane());
        Tab users = new Tab("Users");
        users.setContent(new UserTab());
        this.getTabs().addAll(products,discounts,categories, orders, users);

        categories.setClosable(false);
        products.setClosable(false);
        orders.setClosable(false);
        users.setClosable(false);
        discounts.setClosable(false);


    }
}
