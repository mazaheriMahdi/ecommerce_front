package com.example.store_front.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.regex.Pattern;

public class TextFieldWithLabel extends VBox {

    VBox errorBox;
    private Boolean isCorrect = false;

    public VBox getErrorBox() {
        return errorBox;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public TextFieldWithLabel(String name) {
        super();

        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);

        Label error = new Label("Invalid " + name + " format");
        error.getStyleClass().add("errorText");

        errorBox = new VBox(error);
        errorBox.setAlignment(Pos.CENTER_LEFT);
        errorBox.setMaxWidth(400);


        Label label = new Label(name);
        TextField email = new TextField();
        email.getStyleClass().add("darkAccent");
        email.setPromptText(name.toLowerCase());

        VBox emailBox = new VBox(label);
        emailBox.setAlignment(Pos.CENTER_LEFT);
        emailBox.setMaxWidth(400);


        this.getChildren().addAll(emailBox, email);
    }

    public TextField getTextField() {
        return (TextField) this.getChildren().get(1);
    }

    public void setValidator(Pattern pattern) {
        TextField textField = (TextField) this.getChildren().get(1);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!pattern.matcher(newValue).matches()) {
                this.getChildren().add(2, errorBox);
            } else {
                this.getChildren().remove(errorBox);
                isCorrect = true;
            }
        });

    }
}
