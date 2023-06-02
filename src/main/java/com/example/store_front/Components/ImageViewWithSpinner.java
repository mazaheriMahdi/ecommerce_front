package com.example.store_front.Components;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImageViewWithSpinner extends VBox {
    public ImageViewWithSpinner(String url, double width, double height) {
        super();

        this.setMaxHeight(height);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setMinWidth(width);

        ProgressBar progressBar = new ProgressBar();

        Image image = new Image(url, true);
        ImageView imageView = new ImageView();
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(progressBar);
        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(width);
                imageView.setFitHeight(height);
                this.getChildren().clear();
                this.getChildren().add(imageView);
            }
        }
        );


    }

}
