package com.example.store_front.Components;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.regex.Pattern;

public class ComboBoxWithLabel<T> extends VBox  {

    VBox errorBox;
    private Boolean isCorrect = false;

    public VBox getErrorBox() {
        return errorBox;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }

    public ComboBoxWithLabel(String name  ,T[] items) {
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
        ComboBox<T> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.getStyleClass().add("darkAccent");
        comboBox.setPromptText(name.toLowerCase());

        VBox box = new VBox(label);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setMaxWidth(400);


        this.getChildren().addAll(box, comboBox);
    }


    public ComboBox<T> getComboBox() {
        return (ComboBox<T>) this.getChildren().get(1);
    }

}