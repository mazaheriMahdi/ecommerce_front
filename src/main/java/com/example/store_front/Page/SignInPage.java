package com.example.store_front.Page;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Service.User.UserService;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.regex.Pattern;

public class SignInPage extends BorderPane {
    public SignInPage() {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        VBox vBox = new VBox();
        TextFieldWithLabel name = new TextFieldWithLabel("Name");
        TextFieldWithLabel emailField = new TextFieldWithLabel("Email");
        TextFieldWithLabel passField = new TextFieldWithLabel("Password");
        TextFieldWithLabel image =  new TextFieldWithLabel("avatar_url");
        image.setAlignment(Pos.CENTER);
        image.setValidator(Pattern.compile(".{4,}"));
        emailField.setAlignment(Pos.CENTER);
        emailField.setValidator(Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
        passField.setAlignment(Pos.CENTER);
        passField.setValidator(Pattern.compile(".{4,}"));
        name.setAlignment(Pos.CENTER);
        name.setValidator(Pattern.compile(".{4,}"));


        Button signIn = new Button("sign in");
        signIn.getStyleClass().add("loginPageBtn");
        Button Back = new Button("Back");
        Back.getStyleClass().add("loginPageBtn");

        Back.setOnMouseClicked(event -> {
            this.getScene().getWindow().hide();
        });

        vBox.getChildren().addAll(name, emailField, passField,image, signIn, Back);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);

        signIn.setDefaultButton(true);
        signIn.setOnAction(actionEvent -> {
            if (emailField.getCorrect() && passField.getCorrect() && name.getCorrect()) {
                try {
                    UserService.signIn(
                            name.getTextField().getText(),
                            emailField.getTextField().getText(),
                            passField.getTextField().getText(),
                            image.getTextField().getText()
                    );
                } catch (IOException | InterruptedException e) {
                    CustomAlert customAlert = new CustomAlert("ERROR");
                    customAlert.show();
                }
                this.getScene().getWindow().hide();
            }
        });
    }
}
