package com.example.store_front.Components;


import com.example.store_front.Router.Router;
import com.example.store_front.Service.Cart.CartService;
import com.example.store_front.Service.Order.OrderService;
import com.example.store_front.Service.User.UserService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;


public class NavBar extends HBox {


    //TODO : add sorting and filtering


    private static TextField textField;

    public static TextField getTextField() {
        return textField;
    }


    public NavBar() {
        super();
        this.setHeight(80);
        this.setMinHeight(80);
        this.setMaxHeight(80);
        this.getStyleClass().add("darkAccent");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        try {
            this.init();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        UserService.addOnUserLoginListener(() -> {
            try {
                this.init();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void init() throws IOException, InterruptedException {
        this.getChildren().clear();
        textField = new TextField();
        textField.setPromptText("Search ... ");
        textField.setFocusTraversable(false);


        HBox search = new HBox();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH, "15px");
        icon.setFill(Color.WHITE);
        icon.setOpacity(0.8);
        search.getChildren().addAll(icon, textField);
        search.setSpacing(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.getStyleClass().add("textField");


        Button loginBtn = new Button("Login");
        Button signIn = new Button("Sign in");

        loginBtn.setOnMouseClicked(event -> {
            Router.toLoginPage();
        });
        if (UserService.getIsLoggedIn()) this.getChildren().add(profileBox());
        else this.getChildren().addAll(loginBtn, signIn);
        this.getChildren().add(search);
        if (UserService.getIsLoggedIn()) {
            this.getChildren().add(cartBtn());
        }

    }

    private HBox profileBox() {
        HBox hBox = new HBox();
        Circle circle = new Circle(25);
        circle.maxHeight(50);
        Image image = new Image(UserService.getCurrentUser().getAvatarUrl(), true);
        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                ImagePattern imagePattern = new ImagePattern(image);
                circle.setFill(imagePattern);
            } else {
                System.out.println("Loading ... " + newValue.doubleValue());
            }
        });


        Text first_name = new Text(UserService.getCurrentUser().getName());
        first_name.setFill(Color.WHITE);
        first_name.setFont(Font.font("Microsoft Sans Serif", FontWeight.LIGHT, 20));


        Text credit = new Text(UserService.getCurrentUser().getCredit() + "$");
        credit.setFont(Font.font("Microsoft Sans Serif", FontWeight.BLACK, 13));
        credit.setFill(Color.WHITE);


        //update credit
        OrderService.addOrderCreatedEventListener(() -> {
            credit.setText(UserService.getCurrentUser().getCredit() + "$");
        });


        VBox vBox = new VBox();
        vBox.getChildren().addAll(first_name, credit);
        vBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(circle, vBox);

        hBox.setSpacing(15);
        return hBox;
    }

    public StackPane cartBtn() throws IOException, InterruptedException {
        Text text = GlyphsDude.createIcon(FontAwesomeIcon.SHOPPING_CART, "20px");
        text.setFill(Color.WHITE);


        Button button = new Button();
        button.setGraphic(text);

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(50, 50);
        stackPane.setMaxHeight(50);
        stackPane.getChildren().add(button);


        StackPane countBox = new StackPane();


        Text count = new Text(CartService.getCartItemsCount() + "");


        OrderService.addOrderCreatedEventListener(() -> {
            count.setText(CartService.getCartItemsCount() + "");
        });
        OrderService.addCreditNotEnoughEventListener(() -> {
            count.setText(CartService.getCartItemsCount() + "");
        });
        CartService.addListener(() -> {
            count.setText(CartService.getCartItemsCount() + "");
        });


        count.setFont(Font.font("Poppins", FontWeight.BLACK, 14));

        count.setFill(Color.WHITE);
        count.setOpacity(0.8);

        count.setTextAlignment(TextAlignment.CENTER);
        Circle circle = new Circle(7);
        circle.setFill(Color.RED);
        countBox.setAlignment(Pos.CENTER);
        countBox.setPrefSize(20, 20);

        VBox countBoxVBox = new VBox(countBox);
        countBoxVBox.setAlignment(Pos.TOP_RIGHT);

        countBox.getChildren().

                addAll(circle, count);
        stackPane.getChildren().

                add(countBoxVBox);
        stackPane.setAlignment(Pos.CENTER_LEFT);

        stackPane.setOnMouseClicked(mouseEvent -> Router.toCartPage());
        return stackPane;
    }
}
