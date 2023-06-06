package com.example.store_front.Components;

import javafx.scene.control.TextField;

public class CategoryTextField extends TextField {
    public CategoryTextField(String content , Boolean editable) {
        super();
        this.getStyleClass().add("darkAccent");
        this.setPromptText("Category Name");
        this.setText(content);
        this.setEditable(editable);
    }
}
