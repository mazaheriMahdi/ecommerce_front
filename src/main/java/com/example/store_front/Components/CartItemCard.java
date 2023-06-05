package com.example.store_front.Components;

import com.example.store_front.Models.cart.CartItem;
import com.example.store_front.Service.Cart.CartService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class CartItemCard extends BorderPane {
    public CartItemCard(CartItem cartItem) {
        super();
        this.getStyleClass().add("cartItem");
        ImageViewWithSpinner image = new ImageViewWithSpinner(cartItem.getProduct().getImage() , 50 , 50);
        VBox vBox = new VBox();
        Text name = new Text(cartItem.getProduct().getName());
        name.setFont(Font.font("Poppins" , FontWeight.BLACK , 16));
        name.setFill(Color.WHITE);
        Text count = new Text(cartItem.getQuantity() + "X");
        count.setFill(Color.WHITE);
        Text totalPrice = new Text(cartItem.getTotalPrice() + "$");
        totalPrice.setFill(Color.WHITE);
        totalPrice.setFont(Font.font(Font.getFamilies().get(2) , FontWeight.BLACK , 10));
        vBox.getChildren().addAll(name , count , totalPrice);
        vBox.setAlignment(Pos.CENTER_LEFT);
        Button remove = new Button();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.REMOVE , "15px");
        icon.setFill(Color.WHITE);
        remove.setGraphic(icon);


        HBox imageAndNameBox = new HBox();
        imageAndNameBox.setAlignment(Pos.CENTER_LEFT);
        imageAndNameBox.setSpacing(15);
        imageAndNameBox.getChildren().addAll(image , vBox);
        this.getChildren().addAll(imageAndNameBox, remove);

        VBox rightBox = new VBox(remove);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        VBox leftBox = new VBox(imageAndNameBox);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        this.setPadding(new Insets(10));
        this.setLeft(leftBox);
        this.setRight(rightBox);

        remove.setOnMouseClicked(event -> {
            try {
                CartService.deleteCartItem(cartItem);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
