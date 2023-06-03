package com.example.store_front.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ProductPropertyCard extends BorderPane {
    public ProductPropertyCard(String key , String value) {
        super();
        this.getStyleClass().add("productPropertyCard");
        this.setPadding(new Insets( 10));
        Text keyText = new Text(key);
        keyText.setFont(Font.font("Poppins",14));
        Text valueText = new Text(value);
        keyText.setFill(Color.WHITE);
        valueText.setFill(Color.WHITE);


        VBox right = new VBox();
        right.setAlignment(Pos.CENTER);

        VBox left = new VBox(valueText);
        left.setAlignment(Pos.CENTER);

        right.getChildren().add(keyText);
        BorderPane.setMargin(right , new Insets(0 , 0 , 0 , 20));
        BorderPane.setMargin(left , new Insets(0 , 30 , 0 , 0));
        this.setLeft(right);
        this.setRight(left);








    }
}
