package com.example.store_front.Components;


import com.example.store_front.Models.User;
import com.example.store_front.Router.Router;
import com.example.store_front.Service.User.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.GlyphsDude;
import javafx.scene.text.TextAlignment;

import static com.example.store_front.Constant.FONT_NAME;


public class NavBar  extends HBox {


    //TODO : add sign in functionality
    //TODO : add Cart btn
    //TODO : update profile info when user log in
    //TODO : add profile btn
    //TODO : add profile page
    //TODO : add profile page functionality
    //TODO : add sorting and filtering


    private static TextField textField ;

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

        this.init();
        UserService.addOnUserLoginListener(this::init);

    }

    private void init(){
        this.getChildren().clear();
        textField = new TextField();
        textField.setPromptText("Search ... ");
        textField.setFocusTraversable(false);


        HBox search = new HBox();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH , "15px" );
        icon.setFill(Color.WHITE);
        icon.setOpacity(0.8);
        search.getChildren().addAll(icon , textField);
        search.setSpacing(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.getStyleClass().add("textField");


        Button loginBtn = new Button("Login");
        Button signIn = new Button("Sign in");

        loginBtn.setOnMouseClicked(event -> {
            Router.toLoginPage();
        });
        if(UserService.getIsLoggedIn())this.getChildren().add(profileBox());
        else this.getChildren().addAll(loginBtn  , signIn);
        this.getChildren().add(search);
        if (UserService.getIsLoggedIn()) {
            this.getChildren().add(cartBtn());
        }

    }

    private HBox profileBox(){
        HBox hBox  = new HBox();
        Circle circle = new Circle(25);
        circle.maxHeight(50);
        Image image = new Image(UserService.getCurrentUser().getAvatarUrl(), true);
        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                ImagePattern imagePattern = new ImagePattern(image);
                circle.setFill(imagePattern);
            }else {
                System.out.println("Loading ... "+newValue.doubleValue());
            }
        });

        VBox vBox= new VBox();
        Text first_name = new Text(UserService.getCurrentUser().getName());
        Text credit = new Text(UserService.getCurrentUser().getCredit() + "$");
        credit.setFont(Font.font("Microsoft Sans Serif" , FontWeight.BLACK , 13));
        credit.setFill(Color.WHITE);

        first_name.setFill(Color.WHITE);
        first_name.setFont(Font.font("Microsoft Sans Serif" , FontWeight.LIGHT , 20));

        vBox.getChildren().addAll(first_name , credit);
        vBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(circle , vBox);

        hBox.setSpacing(15);
        return hBox;
    }

    public StackPane cartBtn(){
        StackPane stackPane = new StackPane();
        Button button = new Button();
        Text text = GlyphsDude.createIcon(FontAwesomeIcon.SHOPPING_CART , "20px");
        text.setFill(Color.WHITE);
        button.setGraphic(text);
        stackPane.setPrefSize(50 , 50);
        stackPane.setMaxHeight(50);
        stackPane.getChildren().add(button);



        StackPane countBox = new StackPane();
        Text count = new Text("2");
        count.setFont(Font.font("Poppins" , FontWeight.BLACK , 14));
        for(String name  : Font.getFamilies()){
            System.out.println(name);
        }
        count.setFill(Color.WHITE);
        count.setOpacity(0.8);

        count.setTextAlignment(TextAlignment.CENTER);
        Circle circle = new Circle(7);
        circle.setFill(Color.RED);
        countBox.setAlignment(Pos.CENTER);
        countBox.setPrefSize(20 , 20);

        VBox countBoxVBox = new VBox(countBox);
        countBoxVBox.setAlignment(Pos.TOP_RIGHT);

        countBox.getChildren().addAll(circle , count );
        stackPane.getChildren().add(countBoxVBox);
        stackPane.setAlignment(Pos.CENTER_LEFT);



        return stackPane;
    }
}
