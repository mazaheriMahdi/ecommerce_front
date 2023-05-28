package com.example.store_front.Page;

import com.example.store_front.Components.NavBar;
import com.example.store_front.Models.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class SingleProductPage extends BorderPane {
    public SingleProductPage(Product product) {
        super();

        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        this.setTop(new NavBar());


        ImagePattern imagePattern = new ImagePattern(new Image(product.getImage() , false));
        Rectangle rectangle = new Rectangle(300 , 300  , imagePattern);

        HBox hBox = new HBox();
        VBox vBox = new VBox();
        Text prName = new Text(product.getName());



        prName.setFill(Color.WHITE);
        prName.setFont(Font.font("Arial" , FontWeight.BLACK, 40));


        Text prPrice = new Text(product.getPrice() + "$");
        prPrice.setFill(Color.WHITE);
        prPrice.setFont(Font.font("Arial" , FontWeight.BOLD, 20));


        vBox.getChildren().addAll(prName , prPrice);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);


        hBox.getChildren().addAll(rectangle,vBox);
        hBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        this.setCenter(hBox);
    }
}
