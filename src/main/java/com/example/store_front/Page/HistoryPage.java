package com.example.store_front.Page;

import com.example.store_front.Components.OrderItemCard;
import com.example.store_front.Models.Order;
import com.example.store_front.Models.OrderItem;
import com.example.store_front.Service.Order.OrderService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryPage extends BorderPane {
    public HistoryPage() {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        VBox vBox = new VBox();
        vBox.getStyleClass().add("darkPrimaryBack");
        vBox.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        this.setCenter(scrollPane);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        try {
            int id = 0;
            for (Order order : OrderService.getAcceptedOrders()) {
                List<OrderItemCard> orderItemCards = new ArrayList<>();
                for (OrderItem orderItem : order.getOrderItems()) {
                    orderItemCards.add(new OrderItemCard(orderItem));
                }
                Text name = new Text("Order #" + ++id + " - " +"Total : " +order.getTotalPrice()+"$" + " - "+"Placed at : " + order.getPlacedAt().toString());
                name.setFont(Font.font("Poppins", 15));
                name.setOpacity(0.5);
                VBox nameBox = new VBox(name);
                nameBox.setAlignment(Pos.CENTER_LEFT);
                name.setFill(Color.WHITE);
                vBox.getChildren().add(name);
                vBox.getChildren().addAll(orderItemCards);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        Button button = new Button("back");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT, "10px");
        icon.setFill(Color.WHITE);
        button.setGraphic(icon);
        button.getStyleClass().add("loginPageBtn");
        button.setOnAction(e -> {
            this.getScene().getWindow().hide();
        });
        vBox.getChildren().add(button);
    }

}
