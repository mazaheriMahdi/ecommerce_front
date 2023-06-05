package com.example.store_front.Components;

import com.example.store_front.Models.Application;
import com.example.store_front.Models.Status;
import com.example.store_front.Service.Application.ApplicationService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ApplicationCard extends BorderPane {
    public ApplicationCard(Application application) {
        super();
        this.getStyleClass().add("cartItem");
        this.getStyleClass().add("height200");
        init(application);
    }

    private void init(Application application){

        VBox vBox = new VBox();
        Text name = new Text(application.id() + "");
        name.setFont(Font.font("Poppins" , FontWeight.BLACK , 16));
        name.setFill(Color.WHITE);
        Circle circle = new Circle(5);
        circle.setFill(
                application.status().equals(Status.ACCEPTED) ? Color.GREEN :
                        application.status().equals(Status.PENDING) ? Color.YELLOW : Color.RED
        );
        HBox idAndStatus = new HBox();
        idAndStatus.setSpacing(5);
        idAndStatus.setAlignment(Pos.CENTER_LEFT);
        idAndStatus.getChildren().addAll(name , circle);



        Text content = new Text(application.content());
        content.setWrappingWidth(150);
        content.setFill(Color.WHITE);
        content.setFont(Font.font(Font.getFamilies().get(2) , FontWeight.BLACK , 10));
        vBox.getChildren().addAll(idAndStatus , content);
        vBox.setAlignment(Pos.CENTER_LEFT);
        Button remove = new Button();
        remove.setMaxSize(30 , 30);
        remove.setMinSize(30 , 30);


        Button accept = new Button();
        accept.getStyleClass().add("acceptBtn");
        Text iconAccept = GlyphsDude.createIcon(FontAwesomeIcon.CHECK , "15px");
        iconAccept.setFill(Color.WHITE);
        accept.setGraphic(iconAccept);



        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.REMOVE , "15px");
        icon.setFill(Color.WHITE);
        remove.setGraphic(icon);


        HBox buttonPane = new HBox();
        buttonPane.getChildren().clear();
        buttonPane.setSpacing(20);
        if (!application.status().equals(Status.ACCEPTED))buttonPane.getChildren().add(accept);
        buttonPane.getChildren().add(remove);
        buttonPane.setAlignment(Pos.CENTER);

//        VBox centerButtonPane = new VBox(buttonPane);

        BorderPane.setMargin(buttonPane , new Insets(0 , 20 , 0 , 0));
        BorderPane.setMargin(vBox , new Insets(0 , 0 , 0 , 20));
        vBox.setPadding(new Insets(5));
        this.setPadding(new Insets(5));
        this.setLeft(vBox);
        this.setRight(buttonPane);

        accept.setOnMouseClicked(event -> {
            try {
                ApplicationService.acceptApplication(application);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        remove.setOnMouseClicked(event -> {
            try {
                ApplicationService.rejectApplication(application);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
