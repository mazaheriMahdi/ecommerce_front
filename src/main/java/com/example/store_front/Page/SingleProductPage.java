package com.example.store_front.Page;

import com.example.store_front.Components.ImageViewWithSpinner;
import com.example.store_front.Components.NavBar;
import com.example.store_front.Models.Product;
import com.example.store_front.Service.Cart.CartService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class SingleProductPage extends BorderPane {
    public SingleProductPage(Product product) {
        super();

        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        this.setTop(new NavBar());


        ImageViewWithSpinner imageViewWithSpinner = new ImageViewWithSpinner(product.getImage(), 500, 500);

        HBox hBox = new HBox();
        VBox vBox = new VBox();

        Button addToCart = new Button("Add To Cart");
        Button back = new Button("Back");

        addToCart.setOnMouseClicked(e -> {
            try {
                CartService.addToCart(product.getId() , 1);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        Text cartIcon = GlyphsDude.createIcon(FontAwesomeIcon.SHOPPING_CART , "15px");
        Text backIcon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT , "15px");
        addToCart.setGraphic(cartIcon);
        back.setGraphic(backIcon);


        Text prName = new Text(product.getName());
        prName.setFill(Color.WHITE);
        prName.setFont(Font.font("Arial" , FontWeight.BLACK, 40));


        Text prPrice = new Text(product.getPrice() + "$");
        prPrice.setFill(Color.WHITE);
        prPrice.setFont(Font.font("Arial" , FontWeight.BOLD, 20));


        vBox.getChildren().addAll(prName , prPrice , addToCart , back);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);


        hBox.getChildren().addAll(imageViewWithSpinner,vBox);
        hBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        this.setCenter(hBox);

        TextField comment = new TextField();
        comment.setPromptText("Comment.....");
        comment.getStyleClass().add("darkAccent");
        comment.setAlignment(Pos.CENTER);

        this.setBottom(comment);

    }
}
