package com.example.store_front.Components;

import com.example.store_front.Models.Discount;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DiscountCard extends BorderPane {
    public DiscountCard(Discount discount) {
        super();
        this.getStyleClass().add("cartItem");
        this.getStyleClass().add("height200");
        init(discount);
    }

    private void init(Discount discount) {

        VBox vBox = new VBox();
        Text name = new Text(discount.id() + "");
        name.setFont(Font.font("Poppins", FontWeight.BLACK, 16));
        name.setFill(Color.WHITE);
        Circle circle = new Circle(5);
        circle.setFill(
                discount.dayLeft() > 0 ? Color.GREEN : Color.RED
        );
        HBox idAndStatus = new HBox();
        idAndStatus.setSpacing(5);
        idAndStatus.setAlignment(Pos.CENTER_LEFT);
        idAndStatus.getChildren().addAll(name, circle);


        Text content = new Text(discount.percent() + "%");
        content.setWrappingWidth(150);
        content.setFill(Color.WHITE);
        content.setFont(Font.font(Font.getFamilies().get(2), FontWeight.BLACK, 15));
        vBox.getChildren().addAll(idAndStatus, content);
        vBox.setAlignment(Pos.CENTER_LEFT);


        Text code = new Text("Code : " + discount.code());
        code.setFill(Color.WHITE);
        code.setFont(Font.font("Poppins" , 14));


        this.setCenter(code);

        BorderPane.setMargin(vBox, new Insets(0, 0, 0, 20));
        vBox.setPadding(new Insets(5));
        this.setPadding(new Insets(5));
        this.setLeft(vBox);


    }

}
