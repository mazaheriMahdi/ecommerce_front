package com.example.store_front.Components;

import com.example.store_front.Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UserCard extends BorderPane {
    public UserCard(User user) {
        super();
        this.getStyleClass().add("cartItem");
        VBox info = new VBox();
        Text name = new Text(user.getName());
        name.setFill(Color.WHITE);
        name.setFont(Font.font("Poppins", 16));
        Text email = new Text(user.getEmail());
        email.setFill(Color.WHITE);
        email.setFont(Font.font("Poppins", 16));
        Text isStaff = new Text(user.getIsStaff() ? "Staff" : "Customer");
        isStaff.setFill(Color.WHITE);
        info.getChildren().addAll(name, email, isStaff);
        info.setAlignment(Pos.CENTER_LEFT);
        info.setPadding(new Insets(10));
        this.setLeft(info);
    }

}
