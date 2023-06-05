package com.example.store_front.Components;


import com.example.store_front.Models.Product;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.Cart.CartService;
import de.jensd.fx.glyphs.GlyphsDude;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProductCard extends VBox {
    private ImageViewWithSpinner image;
    private Text name;
    private Text price;

    private boolean isHovered=false;

    public boolean isHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public ProductCard(Product product) {
        super();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("card");
        this.getStyleClass().add("darkAccent");
        this.setSpacing(10);
        this.minWidth(240);
        this.maxHeight(240);


        this.image = new ImageViewWithSpinner(product.getImage(), 120, 120);

        this.name = new Text(product.getName());
        this.name.setFill(Color.WHITE);
        this.name.setFont(Font.font(Font.getFamilies().get(2), FontWeight.BLACK, 15));


        this.price = new Text(product.getPrice() + "$");
        this.price.setFill(Color.WHITE);
        this.price.setFont(Font.font(Font.getFamilies().get(6), FontWeight.BOLD, 14));


        HBox hBox = new HBox();
        Button button = new Button("Add to cart");
        Text icon = GlyphsDude.createIcon(de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.SHOPPING_CART, "15px");
        icon.setFill(Color.WHITE);
        button.setGraphic(icon);
        hBox.getChildren().addAll(button, this.price);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(button, new Insets(0, 10, 0, 0));
        this.getChildren().addAll(this.image, this.name, hBox);


        this.setOnMouseClicked(mouseEvent -> {
            Router.toProductPage(product);
        });

        button.setOnMouseClicked(mouseEvent -> {
            try {
                CartService.addToCart(product.getId(), 1);
                mouseEvent.consume();

            } catch (IOException | InterruptedException e) {
                System.out.println("send data failed");
            }
        });
    }


}
