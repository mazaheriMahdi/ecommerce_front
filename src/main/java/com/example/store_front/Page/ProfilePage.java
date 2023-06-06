package com.example.store_front.Page;

import com.example.store_front.Components.TextFieldWithLabel;
import com.example.store_front.Service.Discount.DiscountService;
import com.example.store_front.Service.User.UserService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProfilePage extends VBox {
    public ProfilePage() {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        TextFieldWithLabel name = new TextFieldWithLabel("Name");
        TextFieldWithLabel email = new TextFieldWithLabel("Email");
        Button submit = new Button("Submit");
        submit.getStyleClass().add("loginPageBtn");
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.SEND, "10px");
        icon.setFill(Color.WHITE);
        submit.setGraphic(icon);

        name.getTextField().setText(UserService.getCurrentUser().getName());
        email.getTextField().setText(UserService.getCurrentUser().getEmail());
        name.setAlignment(Pos.CENTER);
        email.setAlignment(Pos.CENTER);

        submit.setOnAction(e -> {
            try {
                UserService.updateUser(name.getTextField().getText(), email.getTextField().getText());
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button back = new Button("Back");
        back.getStyleClass().add("loginPageBtn");
        Text icon2 = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT, "10px");
        icon2.setFill(Color.WHITE);
        back.setGraphic(icon2);
        back.setOnAction(e -> {
            this.getScene().getWindow().hide();
        });

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        try {
            DiscountService.getAllDiscounts().forEach(
                    discount -> {
                        vBox.getChildren().add(new Text(discount.toString()));
                    }
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        vBox.getStyleClass().add("darkPrimaryBack");
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        scrollPane.setPadding(new Insets(-1));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(name, email, submit ,back , scrollPane);

    }
}
