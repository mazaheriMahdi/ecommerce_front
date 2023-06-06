package com.example.store_front.Page;


import com.example.store_front.Components.CartItemCard;
import com.example.store_front.Models.cart.Cart;
import com.example.store_front.Models.cart.CartItem;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Order.OrderService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BorderPane {

    private Cart cart;

    public CartPage(Cart cart) {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        this.cart = cart;
        init(this.cart);
    }

    private void init(Cart cart) {

        //center of the border pane
        List<CartItemCard> cartItemCarts = new ArrayList<>();
        VBox vBox = new VBox();
        vBox.getStyleClass().add("darkPrimaryBack");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemCarts.add(new CartItemCard(cartItem));
        }
        vBox.getChildren().addAll(cartItemCarts);
        this.setCenter(scrollPane);


        //bottom of the border pane
        HBox hBox = new HBox();
        hBox.getStyleClass().add("darkAccent");
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setMinWidth(500);
        hBox.setMaxWidth(500);
        Text total = new Text("Total: " + cart.getTotalPrice() + "$");
        total.setFont(Font.font("Poppins", FontWeight.BLACK, 20));
        total.setFill(Color.WHITE);
        Button button = new Button("Continue Payment");


        TextField textField = new TextField();
        try {
            if (!CartService.getCart().getDiscount().equals(""))textField.setText(CartService.getCart().getDiscount());
            textField.setPromptText("Discount Code");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        textField.getStyleClass().add("width180");

        Button submitCode = new Button();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.CHECK, "10px");
        icon.setFill(Color.WHITE);
        submitCode.setGraphic(icon);
        submitCode.getStyleClass().add("darkAccent");
        submitCode.setOnMouseClicked(event -> {
            try {
                CartService.applyDiscountCode(textField.getText());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        hBox.getChildren().addAll(total, button, textField, submitCode);
        hBox.setPadding(new Insets(20));
        this.setBottom(hBox);


        button.setOnMouseClicked(event -> {
            try {
                OrderService.createOrders(CartService.getCartId());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
