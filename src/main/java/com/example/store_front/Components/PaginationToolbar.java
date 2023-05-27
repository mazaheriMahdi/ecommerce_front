package com.example.store_front.Components;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PaginationToolbar extends HBox {

    public PaginationToolbar() {
        super();
        this.getStyleClass().add("darkPrimaryBack");
        this.setPadding(new Insets(20));
        Button next = new Button();
        next.getStyleClass().add("paginationBtn");

        Button previous = new Button();
        previous.getStyleClass().add("paginationBtn");

        Button first = new Button();
        first.getStyleClass().add("paginationBtn");

        Button last = new Button();
        last.getStyleClass().add("paginationBtn");

        Text nextText = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_CIRCLE_RIGHT, "20px");
        nextText.setFill(Color.WHITE);
        next.setGraphic(nextText);

        Text previousText = GlyphsDude.createIcon(FontAwesomeIcon.ARROW_CIRCLE_LEFT, "20px");
        previousText.setFill(Color.WHITE);
        previous.setGraphic(previousText);

        Text firstText = GlyphsDude.createIcon(FontAwesomeIcon.FAST_BACKWARD, "10px");
        firstText.setFill(Color.WHITE);
        first.setGraphic(firstText);

        Text lastText = GlyphsDude.createIcon(FontAwesomeIcon.FAST_FORWARD, "10px");
        lastText.setFill(Color.WHITE);
        last.setGraphic(lastText);

        this.getChildren().addAll(first, previous, next, last);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);


    }
}
