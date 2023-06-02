package com.example.store_front.Page;


import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Models.RequestModel.PaymentRequestModel;
import com.example.store_front.Service.PaymentService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.regex.Pattern;

public class PaymentPage extends BorderPane {
    public PaymentPage() {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        TextFieldWithLabel textFieldWithLabel = new TextFieldWithLabel("Card Number");

        textFieldWithLabel.setValidator(Pattern.compile("(\\d{4}-){3}\\d{4}"));

        TextFieldWithLabel textFieldWithLabel1 = new TextFieldWithLabel("CVV2");

        textFieldWithLabel1.setValidator(Pattern.compile("\\d*"));
        TextFieldWithLabel textFieldWithLabel2 = new TextFieldWithLabel("Amount");

        textFieldWithLabel2.setValidator(Pattern.compile("\\d*"));

        Button pay = new Button("Pay");
        pay.getStyleClass().add("loginPageBtn");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.CREDIT_CARD, "10px");
        icon.setFill(Color.WHITE);
        pay.setGraphic(icon);
        pay.setOnMouseClicked(e -> {
            try {
                PaymentService.createPayment(new PaymentRequestModel(
                                textFieldWithLabel.getTextField().getText(),
                                textFieldWithLabel1.getTextField().getText(),
                                Double.parseDouble(textFieldWithLabel2.getTextField().getText()

                                )
                        )
                );
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button back = new Button("Back");
        back.getStyleClass().add("loginPageBtn");
        Text icon1 = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT, "10px");
        icon1.setFill(Color.WHITE);
        back.setGraphic(icon1);
        back.setOnMouseClicked(e -> {
            this.getScene().getWindow().hide();
        });

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(textFieldWithLabel, textFieldWithLabel1, textFieldWithLabel2 , pay , back);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);

    }
}
