package com.example.store_front.Page;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CategoryTab extends BorderPane {
    public CategoryTab() {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");

    }
}
