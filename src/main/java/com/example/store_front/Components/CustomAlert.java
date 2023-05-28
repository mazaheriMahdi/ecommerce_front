package com.example.store_front.Components;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomAlert extends Stage {

    public CustomAlert(String message) {
        super();
        BorderPane borderPane = new BorderPane();
        borderPane.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        borderPane.getStyleClass().add("darkPrimaryBack");
        Text text = new Text(message);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(25));


        VBox iconVBox = new VBox();
        iconVBox.setAlignment(Pos.CENTER);
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.SMILE_ALT, "50px");
        icon.setFill(Color.WHITE);
        icon.setTextAlignment(TextAlignment.CENTER);
        iconVBox.getChildren().add(icon);


        VBox vBox = new VBox();

        Button button = new Button("OK");
        vBox.getChildren().add(button);
        button.setOnAction(e -> this.close());
        borderPane.setPadding(new javafx.geometry.Insets(10));
        button.setPadding(new Insets(30));
        button.setAlignment(Pos.CENTER);
        borderPane.setBottom(button);

        borderPane.setCenter(text);
        borderPane.setRight(iconVBox);
        Scene scene = new Scene(borderPane, 400, 200);

        this.initModality(Modality.APPLICATION_MODAL);

        this.setScene(scene);
    }


}
