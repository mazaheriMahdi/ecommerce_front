package com.example.store_front.Page;

import com.example.store_front.Components.CategoryTextField;
import com.example.store_front.Components.CustomAlert;
import com.example.store_front.Service.Category.CategoryService;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryTab extends BorderPane {
    public CategoryTab() {
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        this.getStyleClass().add("darkPrimaryBack");
        VBox root = new VBox();
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("darkPrimaryBack");
        scrollPane.setPadding(new Insets(-2));
        this.setCenter(scrollPane);

        Button add = new Button();
        Text icon = GlyphsDude.createIcon(FontAwesomeIcon.PLUS_CIRCLE, "10px");
        icon.setFill(Color.WHITE);
        add.setGraphic(icon);
        add.getStyleClass().add("darkAccent");

        Button submit = new Button("Submit");
        submit.getStyleClass().add("loginPageBtn");
        submit.setOnMouseClicked(event -> {
            root.getChildren().forEach(
                    node -> {
                        if (node instanceof CategoryTextField categoryTextField) {
                            if (categoryTextField.isEditable()) {
                                try {
                                    CategoryService.addCategory(categoryTextField.getText());
                                } catch (IOException | InterruptedException e) {
                                    CustomAlert customAlert = new CustomAlert("Error");
                                    customAlert.show();
                                }
                            }
                        }
                    }
            );
        });

        try {
            CategoryService.getAll().forEach(
                    category -> {
                        root.getChildren().add(root.getChildren().size(), new CategoryTextField(category.getName(), false));
                    }
            );
        } catch (IOException | InterruptedException e) {
            CustomAlert customAlert = new CustomAlert("Error");
            customAlert.show();
        }


        add.setOnMouseClicked(event -> {
            if (!root.getChildren().contains(submit)) {
                root.getChildren().add(submit);
            }
            root.getChildren().add(root.getChildren().size() - 2, new CategoryTextField("", true));
        });
        root.getChildren().add(add);
        root.setSpacing(20);
    }
}
