package com.example.store_front.Components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@ToString
public class KeyValueTextFiled extends HBox {
    public KeyValueTextFiled(String[] keys) {
        super();
        ComboBox<String> key = new ComboBox<>();
        key.getItems().addAll(keys);
        key.setEditable(true);
        TextField value = new TextField();
        key.getStyleClass().add("darkAccent");
        value.getStyleClass().add("darkAccent");
        key.getStyleClass().add("width180");
        key.getStyleClass().add("text-field");
        value.getStyleClass().add("width180");
        key.setPromptText("key");
        value.setPromptText("value");





        this.getChildren().addAll(key, value);
        this.setSpacing(20);
    }


    public Map<String , String> getMap(){
        Map<String , String> map = new HashMap<>();
        map.put("name" , ((ComboBox)this.getChildren().get(0)).getValue().toString());
        map.put("value" , ((TextField)this.getChildren().get(1)).getText());
        return map;
    }
}
