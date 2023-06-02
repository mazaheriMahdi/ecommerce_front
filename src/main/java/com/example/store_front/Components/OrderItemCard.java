package com.example.store_front.Components;

import com.example.store_front.Models.OrderItem;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OrderItemCard extends HBox {
    public OrderItemCard(OrderItem orderItem) {
        super();

//        this.getStyleClass().add("cartItem");
//        ImageViewWithSpinner image = new ImageViewWithSpinner(orderItem.getProduct().getImage() , 50 , 50);
//        VBox vBox = new VBox();
//        Text name = new Text(orderItem.getProductId().getName());
//        name.setFont(Font.font("Poppins" , FontWeight.BLACK , 16));
//        name.setFill(Color.WHITE);
//        Text count = new Text(orderItem.getQuantity() + "X");
//        count.setFill(Color.WHITE);
//        Text totalPrice = new Text(orderItem.getTotalPrice() + "$");
//        totalPrice.setFill(Color.WHITE);
//        totalPrice.setFont(Font.font(Font.getFamilies().get(2) , FontWeight.BLACK , 10));
//        vBox.getChildren().addAll(name , count , totalPrice);
//        vBox.setAlignment(Pos.CENTER_LEFT);
//        Button remove = new Button();
//        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.REMOVE , "15px");
//        icon.setFill(Color.WHITE);
//        remove.setGraphic(icon);
//        this.setAlignment(Pos.CENTER);
//        this.setSpacing(200);
//
//        HBox imageAndNameBox = new HBox();
//        imageAndNameBox.setAlignment(Pos.CENTER_LEFT);
//        imageAndNameBox.setSpacing(15);
//        imageAndNameBox.getChildren().addAll(image , vBox);
//        this.getChildren().addAll(imageAndNameBox, remove);

    }
}
