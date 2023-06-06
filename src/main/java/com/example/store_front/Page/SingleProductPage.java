package com.example.store_front.Page;

import com.example.store_front.Components.*;
import com.example.store_front.Exception.LoginFailedException;
import com.example.store_front.Models.Product;
import com.example.store_front.Models.Review;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Review.ReviewService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
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
import java.util.Map;

public class SingleProductPage extends BorderPane {

    //TODO : show productProperties
    //TODO : implement Point system
    public SingleProductPage(Product product, List<Review> reviews) {
        super();

        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");


        ImageViewWithSpinner imageViewWithSpinner = new ImageViewWithSpinner(product.getImage(), 200, 200);

        HBox hBox = new HBox();
        VBox vBox = new VBox();
        VBox root = new VBox();

        Button addToCart = new Button("Add To Cart");
        Button back = new Button("Back");

        addToCart.setOnMouseClicked(e -> {
            try {
                CartService.addToCart(product.getId(), 1);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (LoginFailedException loginFailedException) {
                CustomAlert customAlert = new CustomAlert("You have to login \nyou will be redirect to login page.");
                Router.toLoginPage();
                customAlert.show();
            }
        });
        Text cartIcon = GlyphsDude.createIcon(FontAwesomeIcon.SHOPPING_CART, "15px");
        Text backIcon = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT, "15px");
        cartIcon.setFill(Color.WHITE);
        backIcon.setFill(Color.WHITE);


        addToCart.setTextFill(Color.WHITE);
        back.setTextFill(Color.WHITE);
        addToCart.setGraphic(cartIcon);
        back.setGraphic(backIcon);


        Text prName = new Text(product.getName());
        prName.setFill(Color.WHITE);
        prName.setFont(Font.font("Arial", FontWeight.BLACK, 40));


        Text prPrice = new Text(product.getPrice() + "$");
        prPrice.setFill(Color.WHITE);
        prPrice.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        vBox.getChildren().addAll(prName, prPrice, addToCart, back);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);



        List<ProductPropertyCard> productPropertyCards = new ArrayList<>();
        for (Map<String , String> property : product.getProductProperties()) {
            productPropertyCards.add(new ProductPropertyCard(property.get("name"), property.get("value")));
        }


        List<ReviewBox> reviewBoxes = new ArrayList<>();
        for (Review review : reviews) {
            reviewBoxes.add(new ReviewBox(review));
        }


        hBox.getChildren().addAll(imageViewWithSpinner, vBox);
        hBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));

        root.getChildren().add(hBox);
        root.getChildren().addAll(productPropertyCards);

        Text commentTitle = new Text("Comments");
        commentTitle.setFill(Color.WHITE);
        commentTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(commentTitle);
        root.getChildren().addAll(reviewBoxes);
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setFitToHeight(true);
        root.getStyleClass().add("darkPrimaryBack");
        this.setCenter(scrollPane);

        HBox commentBox = new HBox();
        commentBox.setSpacing(15);
        Button send = new Button();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.SEND, "15px");
        icon.setFill(Color.WHITE);
        send.setGraphic(icon);

        TextField comment = new TextField();
        comment.setPromptText("Comment.....");
        comment.getStyleClass().add("darkAccent");
        commentBox.getChildren().addAll(comment, send);
        commentBox.setAlignment(Pos.CENTER);
        commentBox.setPadding(new Insets(20));
        send.getStyleClass().add("sendBTN");

        comment.setOnMouseClicked(e -> {
            send.setDefaultButton(true);
        });
        send.setOnAction(e -> {

            try {
                ReviewService.sendReview(comment.getText(), product);
                comment.setText("");
            }catch (LoginFailedException loginFailedException){
                CustomAlert customAlert = new CustomAlert("You have to login \nyou will be redirect to login page.");
                Router.toLoginPage();
                customAlert.show();
            }
        });

        back.setOnMouseClicked(e -> {
            this.getScene().getWindow().hide();
        });


        this.setBottom(commentBox);

    }
}
