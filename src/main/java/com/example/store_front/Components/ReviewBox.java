package com.example.store_front.Components;

import com.example.store_front.Models.Review;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReviewBox extends VBox {

    public ReviewBox(Review review ) {
        super();
        Text userName = new Text(review.getUserName());
        Text content = new Text(review.getContent());
        userName.setFill(Color.WHITE);
        content.setFill(Color.WHITE);
        content.setFont(Font.font("Arial" , FontWeight.LIGHT,10));
        userName.setFont(Font.font("Arial" , FontWeight.BLACK,18));
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(userName , content);
        this.getStyleClass().add("commentCard");

    }
}
