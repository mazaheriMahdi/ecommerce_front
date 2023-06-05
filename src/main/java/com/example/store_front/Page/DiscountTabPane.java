package com.example.store_front.Page;

import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Components.DiscountCard;
import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Service.Discount.DiscountService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscountTabPane extends BorderPane {
    public DiscountTabPane() {
        super();
        TextFieldWithLabel percent = new TextFieldWithLabel("Percent");
        TextFieldWithLabel count = new TextFieldWithLabel("Count");
        TextFieldWithLabel day = new TextFieldWithLabel("Day");
        TextFieldWithLabel email = new TextFieldWithLabel("Email");
        Button submit = new Button("Add Discount");
        submit.getStyleClass().add("loginPageBtn");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.PLUS_CIRCLE, "10px");
        icon.setFill(Color.WHITE);
        submit.setGraphic(icon);
        VBox center = new VBox(percent, count, day, email, submit);
        center.setSpacing(20);
        HBox hCenter = new HBox(center);
        center.setAlignment(Pos.CENTER);
        hCenter.setAlignment(Pos.CENTER);
        this.setCenter(hCenter);

        submit.setOnMouseClicked(event -> {
            try {
                DiscountService.addDiscount(
                        Integer.parseInt(percent.getTextField().getText()),
                        Integer.parseInt(count.getTextField().getText()),
                        Integer.parseInt(day.getTextField().getText()),
                        email.getTextField().getText());
                initRight();
            } catch (IOException | InterruptedException e) {
                CustomAlert customAlert = new CustomAlert("Error");
                customAlert.show();
            }
        });
        initRight();






    }

    public void initRight(){

        try {
            VBox vBox = new VBox();
            List<DiscountCard> discountCards = new ArrayList<>();
            DiscountService.getAllDiscounts().forEach(discount -> {
                DiscountCard discountCard = new DiscountCard(discount);
                discountCards.add(discountCard);
            });

            Text title = new Text("Discounts");
            title.setFont(Font.font("Poppins", 35));
            title.setFill(Color.WHITE);
            vBox.getChildren().add(title);
            vBox.getChildren().addAll(discountCards);
            vBox.getStyleClass().add("darkPrimaryBack");
            vBox.setSpacing(20);

            ScrollPane scrollPane = new ScrollPane(vBox);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            scrollPane.setPadding(new Insets(-1));
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            vBox.setPadding(new Insets(20));
            setRight(scrollPane);
        } catch (IOException | InterruptedException e) {
            CustomAlert customAlert = new CustomAlert("Error");
            customAlert.show();
        }

    }
}
