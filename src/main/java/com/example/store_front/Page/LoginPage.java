package com.example.store_front.Page;

import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Service.User.UserService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginPage extends BorderPane {
    public LoginPage() {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        VBox vBox = new VBox();
        TextFieldWithLabel emailField = new TextFieldWithLabel("Email");
        TextFieldWithLabel passField = new TextFieldWithLabel("Password");
        emailField.setAlignment(Pos.CENTER);
        emailField.setValidator(Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
        passField.setAlignment(Pos.CENTER);
        passField.setValidator(Pattern.compile(".{4,}"));


        Button login = new Button("Login");
        login.getStyleClass().add("loginPageBtn");
        Button Back = new Button("Back");
        Back.getStyleClass().add("loginPageBtn");

        Back.setOnMouseClicked(event -> {
            this.getScene().getWindow().hide();
        });

        vBox.getChildren().addAll(emailField, passField, login, Back);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);

        login.setDefaultButton(true);
        login.setOnAction(actionEvent -> {
            if (emailField.getCorrect() && passField.getCorrect()) {

                try {

                    UserService.login(emailField.getTextField().getText(), passField.getTextField().getText());
                    this.getScene().getWindow().hide();


                } catch (IOException | InterruptedException e) {

                }
            } else {
                System.out.println("Invalid");
            }
        });
    }


}
