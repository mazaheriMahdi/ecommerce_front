package com.example.store_front.Components;

import com.example.store_front.Models.OrderItem;
import com.example.store_front.Models.Product;
import com.example.store_front.Service.Product.ProductService;
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
import java.text.DecimalFormat;

public class OrderItemCard extends BorderPane {
    public OrderItemCard(OrderItem orderItem) {
        super();
        Product product ;
        try {
            product = ProductService.getProduct(orderItem.getProductId());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.getStyleClass().add("cartItem");
        ImageViewWithSpinner image = new ImageViewWithSpinner(product.getImage(), 50, 50);
        VBox vBox = new VBox();
        Text name = new Text(product.getName());
        name.setFont(Font.font("Poppins", FontWeight.BLACK, 16));
        name.setFill(Color.WHITE);
        Text count = new Text(orderItem.getQuantity() + "X");
        count.setFill(Color.WHITE);
        Text totalPrice = new Text(orderItem.getTotalPrice() + "$");
        totalPrice.setFill(Color.WHITE);
        totalPrice.setFont(Font.font("Poppins", FontWeight.BLACK, 16));
        totalPrice.setOpacity(0.8);
        vBox.getChildren().addAll(name);
        vBox.setAlignment(Pos.CENTER_LEFT);


        VBox totalPriceBox = new VBox();
        totalPriceBox.getChildren().addAll(totalPrice , count);
        totalPriceBox.setAlignment(Pos.CENTER);


        HBox imageAndNameBox = new HBox();
        imageAndNameBox.setAlignment(Pos.CENTER_LEFT);
        imageAndNameBox.setSpacing(15);
        imageAndNameBox.getChildren().addAll(image, vBox);

        Button button = new Button("back");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT, "1.5em");
        icon.setFill(Color.WHITE);
        button.setGraphic(icon);
        button.getStyleClass().add("loginPageBtn");
        button.setOnAction(e -> {
            this.getScene().getWindow().hide();
        });

        VBox rightBox = new VBox(totalPriceBox);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        VBox leftBox = new VBox(imageAndNameBox);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        this.setPadding(new Insets(10));
        this.setLeft(leftBox);
        this.setRight(rightBox);

    }
}
