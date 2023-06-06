package com.example.store_front.Page.Admin;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Components.UserCard;
import com.example.store_front.Service.User.UserService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserTab extends BorderPane {
    public UserTab() {
        super();
        VBox root = new VBox();
        root.getStyleClass().add("darkPrimaryBack");
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        try {
            UserService.getAll().forEach(user -> {
                root.getChildren().add(new UserCard(user));
            });
        } catch (IOException | InterruptedException e) {
            CustomAlert customAlert = new CustomAlert("Error");
            customAlert.show();
        }

        Button button = new Button("Logout");
        button.getStyleClass().add("loginPageBtn");
        button.setOnMouseClicked(event -> {
            UserService.logout();
        });
        root.getChildren().add(button);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        this.setCenter(scrollPane);
    }
}
